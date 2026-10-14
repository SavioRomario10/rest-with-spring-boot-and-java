package io.savioromario10.rest_spring_java.data.dto.v1;

import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponseDTO() {}

    public UploadFileResponseDTO(String fileDownloadUri, String fileName, String fileType, long size) {
        this.fileDownloadUri = fileDownloadUri;
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }
    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UploadFileResponseDTO that)) return false;
        return getSize() == that.getSize() && Objects.equals(getFileName(), that.getFileName()) &&
                Objects.equals(getFileDownloadUri(), that.getFileDownloadUri()) &&
                Objects.equals(getFileType(), that.getFileType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getFileDownloadUri(), getFileType(), getSize());
    }
}