
package com.hrmsrevamp.util;

import com.hrmsrevamp.constant.MessageEnum;
import com.hrmsrevamp.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExValidator {
  private static final Logger LOGGER = LoggerFactory.getLogger(RegExValidator.class);

  public static void validateEmail(String email) {
    if (Objects.nonNull(email) && !email.isEmpty()) {
      Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(email);
      if (!matcher.find()) {
        LOGGER.error("Given email: {} is invalid", email);
        throw new InvalidRequestException(MessageEnum.YOU_HAVE_ENTERED_AN_INVALID_EMAIL_ADDRESS.name());
      }
    } else {
      LOGGER.error("Given email: {} is invalid", email);
      throw new InvalidRequestException(MessageEnum.YOU_HAVE_ENTERED_AN_INVALID_EMAIL_ADDRESS.name());
    }
  }

  public static void validateMobileNumber(String mobileNumber) {
    if (Objects.nonNull(mobileNumber) && !mobileNumber.isEmpty()) {
      String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
              + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
              + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
      Pattern pattern = Pattern.compile(patterns);
      Matcher m = pattern.matcher(mobileNumber);
      if (!(m.find() && m.group().equals(mobileNumber))
              || (mobileNumber.length() < 11)
              || (mobileNumber.length() > 12)) {
        LOGGER.error("Invalid Mobile Number: {}", mobileNumber);
        throw new InvalidRequestException(MessageEnum.INVALID_MOBILE_NUMBER.name());
      }
    } else {
      LOGGER.error("Mobile Number must not be null or empty");
      throw new InvalidRequestException(MessageEnum.MOBILE_NUMBER_MUST_NOT_BE_NULL_OR_EMPTY.name());
    }
  }
}
