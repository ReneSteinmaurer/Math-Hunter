package model;

public class Vocabulary {
    private String germanWord;
    private String englishWord;
    private String difficulty;

    public Vocabulary(String germanWord, String englishWord, String difficulty){
        this.germanWord=germanWord;
        this.englishWord=englishWord;
        this.difficulty = difficulty;
    }

    public String getGermanWord(){
        return this.germanWord;
    }

    public String getEnglishWord(){
        return this.englishWord;
    }

    public void setGermanWord(String germanWord){
        this.germanWord=germanWord;
    }

    public void setEnglishWord(String englishWord){
        this.englishWord=englishWord;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
