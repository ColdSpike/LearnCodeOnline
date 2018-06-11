package com.pawar.makrand.learncodeonline.retrofit.utils;

import java.util.List;

public class DataStructureResponse {
    public List<QuestionAnswer> questions;

    public static class QuestionAnswer {
        public String question;
        public String Answer;
        @Override
        public boolean equals(Object obj) {
            QuestionAnswer qa = (QuestionAnswer) obj;

            if (this.question.equals(qa.question) && this.Answer.equals(qa.Answer))
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return 199;
        }
    }
}
