package com.example.myapplication;

public class Question {
    private String sound_question,
            image_option1,
            image_option2,
            image_option3,
            image_option4,
            answer_number;

    //EMPTY CONSTRUCTOR TO MAKE AN EMPTY OBJECT OF QUESTION
    public Question() {

    }

    public Question(String sound_question, String image_option1, String image_option2, String image_option3, String image_option4, String answer) {
        this.sound_question = sound_question;
        this.image_option1 = image_option1;
        this.image_option2 = image_option2;
        this.image_option3 = image_option3;
        this.image_option4 = image_option4;
        this.answer_number = answer;
    }

    public String getSound_question() {
        return sound_question;
    }

    public void setSound_question(String sound_question) {
        this.sound_question = sound_question;
    }

    public String getImage_option1() {
        return image_option1;
    }

    public void setImage_option1(String image_option1) {
        this.image_option1 = image_option1;
    }

    public String getImage_option2() {
        return image_option2;
    }

    public void setImage_option2(String image_option2) {
        this.image_option2 = image_option2;
    }

    public String getImage_option3() {
        return image_option3;
    }

    public void setImage_option3(String image_option3) {
        this.image_option3 = image_option3;
    }

    public String getImage_option4() {
        return image_option4;
    }

    public void setImage_option4(String image_option4) {
        this.image_option4 = image_option4;
    }

    public String getAnswer() {
        return answer_number;
    }

    public void setAnswer(String answer_number) {
        this.answer_number = answer_number;
    }
}