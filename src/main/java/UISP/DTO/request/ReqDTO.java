package UISP.DTO.request;

import jakarta.validation.constraints.NotBlank;

public class ReqDTO {
    @NotBlank(message = "email không được để trống")
    private String email;
    @NotBlank(message = "password không được để trống")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
