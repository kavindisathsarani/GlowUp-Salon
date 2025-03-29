package lk.ijse.backend.util;

import lk.ijse.backend.enums.ImageType;
import lk.ijse.backend.exception.ImageExtractionFailedException;
import lk.ijse.backend.exception.ImagePersistFailedException;
import lk.ijse.backend.exception.InvalidImageTypeException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class ImageUtil {
    public static Path IMAGE_DIRECTORY = Paths.get(System.getProperty("user.home"), "Desktop", "LocalS3Bucket").toAbsolutePath().normalize();


    public ImageUtil() {
        System.out.println("ImageUtil constructor called");
        System.out.println("Attempting to create directory: " + IMAGE_DIRECTORY);
        if (!Files.exists(IMAGE_DIRECTORY)) {
            try {
                Files.createDirectories(IMAGE_DIRECTORY);
                System.out.println("Directory created: " + IMAGE_DIRECTORY);
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + IMAGE_DIRECTORY);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Directory already exists: " + IMAGE_DIRECTORY);
        }

    }

    public String getImage(String imageId) {
        try {
            Optional<Path> resource = searchImage(imageId);
            if (resource.isPresent()) {
                return Base64.getEncoder().encodeToString(Files.readAllBytes(resource.get()));
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new ImageExtractionFailedException(imageId);
        }
    }

    public String saveImage(ImageType imageType, MultipartFile file) {
        // Check if the file is empty
        if (file.isEmpty()) {
            return null;
        }
        //Check whether the file types are valid
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith("jpg") &&
                !Objects.requireNonNull(file.getOriginalFilename()).endsWith("png") &&
                !Objects.requireNonNull(file.getOriginalFilename()).endsWith("jpeg")
        ) {
            throw new InvalidImageTypeException("Invalid file type. Only JPG and PNG files are allowed.");
        }

        String fileName = imageType.toString() + "-" + UUID.randomUUID();
        try {
            Files.copy(file.getInputStream(), IMAGE_DIRECTORY.resolve(fileName + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1]));
            return fileName;
        } catch (IOException e) {
            throw new ImagePersistFailedException("Failed to save image");
        }
    }

    public String updateImage(String imageId, ImageType imageType, MultipartFile file) {
        try {
            Optional<Path> resource = searchImage(imageId);
            if (resource.isPresent()) {
                Files.delete(resource.get());
            }
            return saveImage(imageType, file);
        } catch (IOException e) {
            throw new ImagePersistFailedException("Failed to update image");
        }
    }


    private Optional<Path> searchImage(String imageId) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(IMAGE_DIRECTORY, imageId + ".*")) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    return Optional.of(entry);
                }
            }
        } catch (IOException e) {
            throw new ImageExtractionFailedException(imageId);
        }
        return Optional.empty();
    }

    public void deleteImage(String imageId) {
        try {
            Optional<Path> resource = searchImage(imageId);
            if (resource.isPresent()) {
                Files.delete(resource.get());
            }
        } catch (IOException e) {
            throw new ImagePersistFailedException("Failed to delete image: "+imageId);
        }
    }

    public String toBase64(MultipartFile file) {
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            throw new ImageExtractionFailedException("Failed to convert image to base64");
        }
    }
}
