package yoon.test.sessionTest1.enums;

import lombok.Getter;

@Getter
public enum Role {

    GUEST("ROLE_ANONYMOUS"),
    USER("ROLE_USER"),
    ADMIN("ROLE,ADMIN");

    private final String key;

    Role(String key){
        this.key = key;
    }

}
