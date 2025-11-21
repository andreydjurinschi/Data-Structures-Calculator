package org.logic.normalizer;



import org.logic.exceptions.IllegalSymbolException;
import org.logic.validator.Validator;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Normalizer {

    private static final Logger log = Logger.getLogger(Normalizer.class.getName());

    public static String normalize(String expression) throws IllegalSymbolException {
        log.info("Normalizer called: ");
        StringBuilder result = new StringBuilder();

        Pattern pattern = Pattern.compile("-?\\d*\\.?\\d+|[()+\\-*/^]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            result.append(matcher.group()).append(" ");
        }

        Validator.validate(result.toString());
        log.info("Normalizer result: " + result.toString());
        return result.toString();
    }


}
