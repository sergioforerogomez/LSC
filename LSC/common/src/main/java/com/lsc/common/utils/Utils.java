package com.lsc.common.utils;

import com.lsc.common.dtos.PracticeDTO;
import com.lsc.common.dtos.WordDTO;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Utils {
    private static Map<String, String> wordSchemaMap;
    private static Map<String, PracticesInterface> practicesMap;
    private static Map<String, WordsInterface> wordsMap;

    private static void fillWordSchemaMap() {
        wordSchemaMap = new HashMap<>();
        wordSchemaMap.put("Abecedario", "Abecedario");
        wordSchemaMap.put("Números", "Números");
        wordSchemaMap.put("Pronombres", "Pronombres");
        wordSchemaMap.put("Relaciones", "Relaciones");
        wordSchemaMap.put("Actividades cotidianas", "Sustantivo - Actividades cotidianas");
        wordSchemaMap.put("En el estudio", "Sustantivo - En el estudio");
        wordSchemaMap.put("Lugares", "Sustantivo - Verbo - Lugares");
        wordSchemaMap.put("Tiempos", "Sustantivo - Verbo - Lugares - Tiempos");
    }

    private static String[] copyArrayItself(String[] array) {
        String[] copy = new String[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
            copy[i + array.length] = array[array.length % (array.length - i)];
        }
        return copy;
    }

    private static void fillPracticesMap() {
        practicesMap = new HashMap<>();
        practicesMap.put("show-sign", (lessonName, wordDTOS, words, word) ->
                {
                    List<String[]> answer = Collections.singletonList(alterWords(wordDTOS, words));
                    return new PracticeDTO().setCode("show-sign").setVideos(answer).setWords(answer);
                }
        );
        practicesMap.put("which-one-videos", (lessonName, wordDTOS, words, word) ->
                {
                    String[] answer = alterWords(wordDTOS, words);
                    words[words.length - 1] = lessonName;
                    List<String[]> content = new ArrayList<>(Collections.singletonList(answer));
                    content.addAll(getContent(wordDTOS, words, 4));
                    Collections.shuffle(content);
                    return new PracticeDTO().setCode("which-one-videos").setVideos(content).setWords(Collections.singletonList(answer)).setAnswer(Collections.singletonList(content.indexOf(answer)));
                }
        );
        practicesMap.put("which-one-video", (lessonName, wordDTOS, words, word) ->
                {
                    String[] answer = alterWords(wordDTOS, words);
                    words[words.length - 1] = lessonName;
                    List<String[]> content = new ArrayList<>(Collections.singletonList(answer));
                    content.addAll(getContent(wordDTOS, words, 3));
                    Collections.shuffle(content);
                    return new PracticeDTO().setCode("which-one-video").setVideos(Collections.singletonList(answer)).setWords(content).setAnswer(Collections.singletonList(content.indexOf(answer)));
                }
        );
        practicesMap.put("translate-video", (lessonName, wordDTOS, words, word) ->
                {
                    String[] answer = alterWords(wordDTOS, words);
                    List<String[]> content = generateRandomWords(wordDTOS, answer, 8);
                    return new PracticeDTO().setCode("translate-video").setVideos(Collections.singletonList(answer)).setWords(content).setAnswer(searchForAnswer(content, answer));
                }
        );
        practicesMap.put("discover-image", (lessonName, wordDTOS, words, word) ->
                {
                    String[] answer = alterWords(wordDTOS, words);
                    words[words.length - 1] = lessonName;
                    List<String[]> content = new ArrayList<>(Collections.singletonList(answer));
                    content.addAll(getContent(wordDTOS, words, 5));
                    Collections.shuffle(content);
                    return new PracticeDTO().setCode("discover-image").setVideos(Collections.singletonList(answer)).setWords(content).setAnswer(Collections.singletonList(content.indexOf(answer)));
                }
        );
    }

    private static WordDTO getRandomLesson(List<WordDTO> wordDTOS, String filter) {
        List<WordDTO> lessonWordDTOS = wordDTOS.stream().filter((item) -> item.getLesson().equals(filter)).collect(toList());
        return lessonWordDTOS.get(new Random().nextInt(lessonWordDTOS.size()));
    }

    private static WordDTO getRandomLevel(List<WordDTO> wordDTOS, String filter) {
        List<WordDTO> levelWordDTOS = wordDTOS.stream().filter((item) -> item.getLevel().equals(filter)).collect(toList());
        return levelWordDTOS.get(new Random().nextInt(levelWordDTOS.size()));
    }

    private static void fillWordsMap() {
        wordsMap = new HashMap<>();
        wordsMap.put("Abecedario", (wordDTOS) -> getRandomLesson(wordDTOS, "Abecedario"));
        wordsMap.put("Números", (wordDTOS) -> getRandomLesson(wordDTOS, "Números"));
        wordsMap.put("Pronombres", (wordDTOS) -> getRandomLesson(wordDTOS, "Pronombres"));
        wordsMap.put("Relaciones", (wordDTOS) -> getRandomLesson(wordDTOS, "Relaciones"));
        wordsMap.put("Sustantivo", (wordDTOS) -> getRandomLevel(wordDTOS, "Sustantivos"));
        wordsMap.put("Actividades cotidianas", (wordDTOS) -> getRandomLesson(wordDTOS, "Actividades cotidianas"));
        wordsMap.put("En el estudio", (wordDTOS) -> getRandomLesson(wordDTOS, "En el estudio"));
        wordsMap.put("Verbo", (wordDTOS) -> getRandomLevel(wordDTOS, "Verbos"));
        wordsMap.put("Lugares", (wordDTOS) -> getRandomLesson(wordDTOS, "Lugares"));
        wordsMap.put("Tiempos", (wordDTOS) -> getRandomLesson(wordDTOS, "Tiempos"));
    }

    private static String[] alterWords(List<WordDTO> wordDTOS, String... words) {
        List<String> alteredWords = new ArrayList<>();
        for (String word : words) {
            WordDTO wordDTO = wordsMap.containsKey(word) ? wordsMap.get(word).get(wordDTOS) : wordDTOS.stream().filter((item) -> item.getWord().equals(word)).findFirst().get();
            wordDTOS.remove(wordDTO);
            alteredWords.add(wordDTO.getWord());
        }
        return alteredWords.toArray(new String[alteredWords.size()]);
    }

    private static List<String[]> getContent(List<WordDTO> wordDTOS, String[] words, int quantity) {
        List<String[]> content = new ArrayList<>();
        IntStream.range(0, quantity - 1).forEach((i) ->
                content.add(alterWords(wordDTOS, words))
        );
        return content;
    }

    private static List<String[]> generateRandomWords(List<WordDTO> wordDTOS, String[] words, int quantity) {
        String[] content = new String[quantity];
        List<WordsInterface> valuesList = new ArrayList<>(wordsMap.values());
        IntStream.range(0, words.length).forEach((i) -> {
            content[i] = words[i];
        });
        IntStream.range(words.length, quantity).forEach((i) -> {
            WordDTO wordDTO = valuesList.get(new Random().nextInt(valuesList.size())).get(wordDTOS);
            wordDTOS.remove(wordDTO);
            content[i] = wordDTO.getWord();
        });
        List<String> aux = Arrays.asList(content);
        Collections.shuffle(aux);
        List<String[]> contentList = new ArrayList<>();
        contentList.add(aux.toArray(new String[aux.size()]));
        return contentList;
    }

    private static int searchInArray(String[] array, String word) {
        int answer = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(word)) {
                answer = i;
            }
        }
        return answer;
    }

    private static List<Integer> searchForAnswer(List<String[]> content, String[] words) {
        List<Integer> answer = new ArrayList<>();
        for (String word : words) {
            answer.add(searchInArray(content.get(0), word));
        }
        return answer;
    }

    private static List<WordDTO> getWordsByLessonName(String lessonName, List<WordDTO> wordDTOS) {
        List<WordDTO> lessonWordDTOS = wordDTOS.stream().filter((item) -> item.getLesson().equals(lessonName)).collect(toList());
        Collections.shuffle(lessonWordDTOS);
        return lessonWordDTOS;
    }

    private static List<PracticeDTO> getRandomPractices(String lessonName, List<WordDTO> wordDTOS, String wordSchema) {
        List<PracticeDTO> practiceDTOS = new ArrayList<>();
        Stack<WordDTO> showSignWords = new Stack<>();
        List<WordDTO> practicesWords = new ArrayList<>();
        List<PracticesInterface> practiceMapValues = new ArrayList<>(practicesMap.values());
        showSignWords.addAll(getWordsByLessonName(lessonName, wordDTOS));
        while (!showSignWords.isEmpty() || !practicesWords.isEmpty()) {
            if (!showSignWords.isEmpty() && new Random().nextInt() % 2 == 0) {
                WordDTO wordDTO = showSignWords.pop();
                practicesWords.add(wordDTO);
                practiceDTOS.add(practicesMap.get("show-sign").get(lessonName, new ArrayList<>(wordDTOS), wordSchema.split(" - "), wordDTO.getWord()));
            } else if (!practicesWords.isEmpty()) {
                WordDTO wordDTO = practicesWords.remove(0);
                practiceDTOS.add(practiceMapValues.get(new Random().nextInt(practiceMapValues.size())).get(lessonName, new ArrayList<>(wordDTOS), wordSchema.split(" - "), wordDTO.getWord()));
            }
            Collections.shuffle(practicesWords);
        }
        return practiceDTOS;
    }

    private static void fill() {
        if (wordSchemaMap == null) {
            fillWordSchemaMap();
        }
        if (practicesMap == null) {
            fillPracticesMap();
        }
        if (wordsMap == null) {
            fillWordsMap();
        }
    }

    public static List<PracticeDTO> getPracticesByLessonName(String lessonName, List<WordDTO> wordDTOS) {
        fill();
        String wordSchema = wordSchemaMap.get(lessonName);
        return getRandomPractices(lessonName, wordDTOS, wordSchema);
    }
}
