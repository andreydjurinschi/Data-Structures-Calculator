package org.logic.normalizer;



import org.logic.exceptions.IllegalSymbolException;
import org.logic.validator.Validator;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// "-?\\d*\\.?\\d+|[()+\\-*/^]"

public class Normalizer {

    private static final Logger log = Logger.getLogger(Normalizer.class.getName());

    public static String normalize(String expression) throws IllegalSymbolException {
        log.info("Normalizer called, caught data: " + expression);
        StringBuilder result = new StringBuilder();

        Pattern pattern = Pattern.compile("-?\\d*\\.?\\d+|[()+*/^\\-]");
        Matcher matcher = pattern.matcher(expression);

        while(matcher.find()){
            result.append(matcher.group()).append(" ");
        }

        log.info("Normalizer finished it`s work, result: " + result);
        Validator.validate(result.toString());

        return result.toString();
    }

    public static void main(String[] args) throws IllegalSymbolException {
        Normalizer.normalize("5565 - - 5");
        Normalizer.normalize("50 - 5+8 +");
        Normalizer.normalize("(27 * 88) - 99 + 23 - (120/80)");
    }


}
