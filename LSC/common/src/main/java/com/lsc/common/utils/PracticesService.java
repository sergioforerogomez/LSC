package com.lsc.common.utils;

import com.lsc.common.dtos.PracticeDTO;
import com.lsc.common.dtos.WordDTO;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class PracticesService {
    private static Map<String, String> wordsSchemaMap;
    private static Map<String, PracticesInterface> practicesMap;
    private static Map<String, WordsInterface> wordsMap;

    private static void fillWordsSchemaMap() {
        wordsSchemaMap = new HashMap<>();
        wordsSchemaMap.put("Abecedario", "Abecedario");
        wordsSchemaMap.put("Números", "Números");
        wordsSchemaMap.put("Pronombres", "Pronombres");
        wordsSchemaMap.put("Relaciones", "Relaciones");
        wordsSchemaMap.put("Actividades cotidianas", "Sustantivo - Actividades cotidianas");
        wordsSchemaMap.put("En el estudio", "Sustantivo - En el estudio");
        wordsSchemaMap.put("Lugares", "Sustantivo - Verbo - Lugares");
        wordsSchemaMap.put("Tiempos", "Sustantivo - Verbo - Lugares - Tiempos");
    }

    private static String[] getWordsSchemaWithAnswer(String[] wordsSchema, String answer) {
        wordsSchema[wordsSchema.length - 1] = answer;
        return wordsSchema;
    }

    private static String alterWord(List<WordDTO> wordDTOS, String word) {
        WordDTO wordDTO = wordsMap.containsKey(word) ? wordsMap.get(word).get(wordDTOS) : wordDTOS.stream().filter((item) -> item.getWord().equals(word)).findFirst().get();
        wordDTOS.remove(wordDTO);
        return wordDTO.getWord();
    }

    private static String[] alterWordsSchema(List<WordDTO> wordDTOS, String... wordsSchema) {
        List<String> words = new ArrayList<>();
        for (String word : wordsSchema) {
            words.add(alterWord(wordDTOS, word));
        }
        return words.toArray(new String[words.size()]);
    }

    private static List<String[]> getRandomVideosOrWords(List<WordDTO> wordDTOS, String[] wordsSchema, int quantity) {
        List<String[]> content = new ArrayList<>();
        IntStream.range(0, quantity - 1).forEach((i) ->
                content.add(alterWordsSchema(wordDTOS, wordsSchema))
        );
        return content;
    }

    private static List<String> getRandomPictures(List<WordDTO> wordDTOS, String lessonName, int quantity) {
        List<String> content = new ArrayList<>();
        IntStream.range(0, quantity - 1).forEach((i) ->
                content.add(alterWord(wordDTOS, lessonName))
        );
        return content;
    }

    private static List<String[]> shuffleArrayToList(String[] array) {
        List<String> list = Arrays.asList(array);
        Collections.shuffle(list);
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(list.toArray(new String[list.size()]));
        return arrayList;
    }

    private static List<String[]> getRandomWords(List<WordDTO> wordDTOS, String[] answer, int quantity) {
        String[] content = new String[quantity];
        List<WordsInterface> valuesList = new ArrayList<>(wordsMap.values());
        IntStream.range(0, answer.length).forEach((i) -> {
            content[i] = answer[i];
        });
        IntStream.range(answer.length, quantity).forEach((i) -> {
            WordDTO wordDTO = valuesList.get(new Random().nextInt(valuesList.size())).get(wordDTOS);
            wordDTOS.remove(wordDTO);
            content[i] = wordDTO.getWord();
        });
        return shuffleArrayToList(content);
    }

    private static List<Integer> searchAnswerInContent(String[] content, String[] answer) {
        List<Integer> answerList = new ArrayList<>();
        for (String word : answer) {
            answerList.add(IntStream.range(0, content.length).filter((i) -> content[i].equals(word)).findFirst().getAsInt());
        }
        return answerList;
    }

    private static void fillPracticesMap() {
        practicesMap = new HashMap<>();
        practicesMap.put("show-sign", (lessonName, wordDTOS, wordsSchema, word) ->
                {
                    List<String[]> answer = Collections.singletonList(new String[]{word});
                    return new PracticeDTO().setCode("show-sign").setVideos(answer).setWords(answer);
                }
        );
        practicesMap.put("take-sign", ((lessonName, wordDTOS, wordsSchema, word) ->
                new PracticeDTO().setCode("take-sign").setWords(Collections.singletonList(new String[]{word}))
        ));
        practicesMap.put("which-one-videos", (lessonName, wordDTOS, wordsSchema, word) ->
                {
                    String[] answer = alterWordsSchema(wordDTOS, getWordsSchemaWithAnswer(wordsSchema.clone(), word));
                    List<String[]> content = new ArrayList<>(Collections.singletonList(answer));
                    content.addAll(getRandomVideosOrWords(wordDTOS, wordsSchema, 4));
                    Collections.shuffle(content);
                    return new PracticeDTO().setCode("which-one-videos").setVideos(content).setWords(Collections.singletonList(answer)).setAnswer(Collections.singletonList(content.indexOf(answer)));
                }
        );
        practicesMap.put("which-one-video", (lessonName, wordDTOS, wordsSchema, word) ->
                {
                    String[] answer = alterWordsSchema(wordDTOS, getWordsSchemaWithAnswer(wordsSchema.clone(), word));
                    List<String[]> content = new ArrayList<>(Collections.singletonList(answer));
                    content.addAll(getRandomVideosOrWords(wordDTOS, wordsSchema, 3));
                    Collections.shuffle(content);
                    return new PracticeDTO().setCode("which-one-video").setVideos(Collections.singletonList(answer)).setWords(content).setAnswer(Collections.singletonList(content.indexOf(answer)));
                }
        );
        practicesMap.put("translate-video", (lessonName, wordDTOS, wordsSchema, word) ->
                {
                    String[] answer = alterWordsSchema(wordDTOS, getWordsSchemaWithAnswer(wordsSchema.clone(), word));
                    List<String[]> words = getRandomWords(wordDTOS, answer, 8);
                    return new PracticeDTO().setCode("translate-video").setVideos(Collections.singletonList(answer)).setWords(words).setAnswer(searchAnswerInContent(words.get(0), answer));
                }
        );
        practicesMap.put("discover-image", (lessonName, wordDTOS, wordsSchema, word) ->
                {
                    List<String> content = new ArrayList<>(Collections.singletonList(word));
                    wordDTOS.remove(wordDTOS.stream().filter((item) -> item.getWord().equals(word)).findFirst().get());
                    content.addAll(getRandomPictures(wordDTOS, lessonName, 5));
                    Collections.shuffle(content);
                    return new PracticeDTO().setCode("discover-image").setVideos(Collections.singletonList(new String[]{word})).setPictures(content).setAnswer(Collections.singletonList(content.indexOf(word)));
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

    private static List<WordDTO> getWordsByLessonName(String lessonName, List<WordDTO> wordDTOS) {
        List<WordDTO> lessonWordDTOS = wordDTOS.stream().filter((item) -> item.getLesson().equals(lessonName)).collect(toList());
        Collections.shuffle(lessonWordDTOS);
        return lessonWordDTOS;
    }

    private static PracticesInterface getRadomPractice(String lessonName) {
        List<String> practicesMapKeySet = new ArrayList<>(practicesMap.keySet());
        practicesMapKeySet.remove("show-sign");
        practicesMapKeySet.remove("take-sign");
        return lessonName.equals("Abecedario") || lessonName.equals("Números") ? practicesMap.get("take-sign") : practicesMap.get(practicesMapKeySet.get(new Random().nextInt(practicesMapKeySet.size())));
    }

    private static void addWord(List<WordDTO> practicesWords, WordDTO wordDTO) {
        String word = wordDTO.getWord();
        if (!word.equals("G") && !word.equals("H") && !word.equals("J") && !word.equals("Ñ") && !word.equals("S") && !word.equals("Z")) {
            practicesWords.add(wordDTO);
        }
    }

    private static List<PracticeDTO> getRandomPractices(String lessonName, List<WordDTO> wordDTOS, String wordsSchema) {
        List<PracticeDTO> practiceDTOS = new ArrayList<>();
        Stack<WordDTO> showSignWords = new Stack<>();
        List<WordDTO> practicesWords = new ArrayList<>();
        showSignWords.addAll(getWordsByLessonName(lessonName, wordDTOS));
        while (!showSignWords.isEmpty() || !practicesWords.isEmpty()) {
            if (!showSignWords.isEmpty() && new Random().nextInt() % 2 == 0) {
                WordDTO wordDTO = showSignWords.pop();
                addWord(practicesWords, wordDTO);
                practiceDTOS.add(practicesMap.get("show-sign").get(lessonName, new ArrayList<>(wordDTOS), wordsSchema.split(" - "), wordDTO.getWord()));
            } else if (!practicesWords.isEmpty()) {
                practiceDTOS.add(getRadomPractice(lessonName).get(lessonName, new ArrayList<>(wordDTOS), wordsSchema.split(" - "), practicesWords.remove(0).getWord()));
            }
            Collections.shuffle(practicesWords);
        }
        return practiceDTOS;
    }

    private static void fill() {
        if (wordsSchemaMap == null) {
            fillWordsSchemaMap();
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
        String wordsSchema = wordsSchemaMap.containsKey(lessonName) ? wordsSchemaMap.get(lessonName) : "";
        return getRandomPractices(lessonName, wordDTOS, wordsSchema);
    }
}
