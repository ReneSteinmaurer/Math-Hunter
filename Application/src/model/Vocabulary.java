package model;

public class Vocabulary {
    private String germanWord;
    private String englishWord;

    public Vocabulary(String germanWord, String englishWord){
        this.germanWord=germanWord;
        this.englishWord=englishWord;
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


}
