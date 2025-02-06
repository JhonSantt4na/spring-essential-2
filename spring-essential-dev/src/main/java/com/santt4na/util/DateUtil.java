package com.santt4na.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
   public String formatLocalTimeDataBaseStyle(LocalDateTime localDateTime) {
      return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
   }
}
