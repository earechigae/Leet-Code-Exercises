package org.codesignal.exercises.optional;

import java.util.Optional;

public class OptionalExercise {
    public static void main(String[] args){
        OptionalExercise optionalExercise = new OptionalExercise();
        Optional<String> opt;
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent() + ", " + empty + ", " + empty.toString() + ", " + empty.orElse("default"));

        String name = null;
        try {
            opt = Optional.of(name);
        }catch(Exception ex){
             opt = Optional.ofNullable(name);
        }
        System.out.println(opt.isPresent() + ", " + opt + ", " + opt.toString() + ", " + opt.orElse("default"));

        opt = Optional.of("Baeldung");
        System.out.println(opt.isPresent() + ", " + opt + ", " + opt.toString() + ", " + opt.orElse("default"));
        System.out.println();

        optionalExercise.whenOrElseGetAndOrElseDiffer_thenCorrect();
    }

    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet: (The getMyDefault function Is NOT called if text is present!!))");
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        System.out.println("Text present: " + defaultText);
        System.out.println();

        System.out.println("Using orElse:  (The getMyDefault function Is called even if text is present!!))");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        System.out.println("Text present: " + defaultText);
    }

    protected String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
}
