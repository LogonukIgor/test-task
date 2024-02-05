package by.logonuk.dto.responce.user;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String name;

    private String login;

    private List<UserAccountForSearchResponse> accounts;
}
