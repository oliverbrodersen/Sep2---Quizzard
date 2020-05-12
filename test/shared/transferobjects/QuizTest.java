package shared.transferobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    @Test
    void GetTitleWithEmptyQuiz()
    {
        Quiz quiz = new Quiz(null,null,null,0);
        assertNull(quiz.getTitle());
    }

    @Test
    void GetLengthWithEmptyQuiz()
    {
        Quiz quiz = new Quiz(null,null,null,0);
        Assertions.assertThrows(NullPointerException.class, quiz::getLength);
    }

    @Test
    void GetNextQuestionWhenSingleQuestion()
    {
        Question question = new Question(null,null,0,0);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        Quiz quiz = new Quiz(null,null,questions,0);

        assertEquals(0,quiz.nextQuestion());
    }

    @Test
    void GetNextNextQuestionWhenSingleQuestion()
    {
        Question question = new Question(null,null,0,0);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        Quiz quiz = new Quiz(null,null,questions,0);
        quiz.nextQuestion();
        assertEquals(-1,quiz.nextQuestion());
    }

    @Test
    void setQuestionWhenSingleQuestion()
    {
        Question question = new Question(null,null,0,0);
        Question question2 = new Question(null,null,0,0);
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        Quiz quiz = new Quiz(null,null,questions,0);

        quiz.setQuestion(question2,0);

        assertEquals(question2,quiz.getQuestion(0));
    }


}