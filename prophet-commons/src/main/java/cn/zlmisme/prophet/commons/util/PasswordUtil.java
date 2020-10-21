package cn.zlmisme.prophet.commons.util;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Objects;

/**
 * @author liming zeng
 * @create 2020-10-20 16:13
 */
public class PasswordUtil {

    private PasswordUtil() { throw new IllegalStateException("Utility class");}

    @Data
    public static class PasswordInfo {
        private String password;
        private String salt;
    }

    public static PasswordInfo generate(String originPassword) {

        final PasswordInfo passwordInfo = new PasswordInfo();
        final String salt = RandomStringUtils.randomAlphanumeric(8);
        passwordInfo.setPassword(DigestUtils.md5Hex(DigestUtils.md5Hex(originPassword) + salt));
        passwordInfo.setSalt(salt);
        return passwordInfo;
    }

    public static boolean check(PasswordInfo passwordInfo, String inputPassword) {
        if (Objects.isNull(passwordInfo) || Objects.isNull(passwordInfo.password) || Objects.isNull(passwordInfo.salt)) {
            return false;
        }
        return DigestUtils.md5Hex(DigestUtils.md5Hex(inputPassword) + passwordInfo.getSalt()).equals(passwordInfo.getPassword());
    }
}
