package module11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) {
        StreamPractice streamPractice = new StreamPractice();
        List<String> names = Arrays.asList("John", "Bill", "Max", "Alex", "Sam", "Pamela", "Dave", "Pascal", "Erik", "Mary");
        String[] stringNumbers = {"1, 2, 0", "4, 5", "11, 22", "-5, -10"};
        System.out.println("--- Task 1 ---");
        System.out.println(streamPractice.getOddNamesAsString(names));
        System.out.println("--- Task 2 ---");
        System.out.println(streamPractice.toUpperCaseAndSortDesc(names));
        System.out.println("--- Task 3 ---");
        System.out.println(streamPractice.getNumbersSorted(stringNumbers));
        System.out.println("--- Task 4 ---");
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        System.out.println(streamPractice.generateRandomNumbers(a, c, m)
                .limit(10)
                .collect(Collectors.toList()));
        System.out.println("--- Task 5 ---");
        Stream<String> s1 = Stream.of("one", "two", "three", "four");
        Stream<String> s2 = Stream.of("1", "2", "3");
        System.out.println(StreamPractice.zip(s1, s2).collect(Collectors.toList()));
        s1 = Stream.of();
        s2 = Stream.of("1", "2", "3");
        System.out.println(StreamPractice.zip(s1, s2).collect(Collectors.toList()));
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> i1 = first.iterator();
        Iterator<T> i2 = second.iterator();
        return Stream.generate(() -> {
                    if (i1.hasNext() && i2.hasNext()) {
                        return List.of(i1.next(), i2.next());
                    }
                    return null;
                })
                .takeWhile(Objects::nonNull)
                .flatMap(Collection::stream);
    }

    public String getOddNamesAsString(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(index -> index % 2 != 0)
                .mapToObj(index -> index + ". " + names.get(index))
                .collect(Collectors.joining(", "));
    }

    public List<String> toUpperCaseAndSortDesc(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public Object getNumbersSorted(String[] array) {
        Pattern pattern = Pattern.compile("(-?\\d+)");
        return Arrays.stream(array)
                .flatMap(str -> pattern.matcher(str).results())
                .map(matchResult -> Integer.valueOf(matchResult.group()))
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public Stream<Long> generateRandomNumbers(long a, long c, long m) {
        return Stream.iterate(0L, x -> (a * x + c) % m);
    }
}
