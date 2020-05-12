package shared.transferobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {


    @Test
    void TestingGetAnswerWhenAnswerIsNull()
    {
        Answer answer = new Answer(null,null,null);
        assertNull(answer.getAnswer());
    }

    @Test
    void TestingGetCorrectWhenAnswerIsNull()
    {
        Answer answer = new Answer(null,null,null);
        assertNull(answer.getCorrect());
    }

    @Test
    void TestingGetAnswerIDWhenAnswerIsNull()
    {
        Answer answer = new Answer(null,null,null);
        assertNull(answer.getAnswerID());
    }


}