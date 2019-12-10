package part4.lesson16.task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Ekaterina Belolipetskaya
 */
public class Main {
    public static void main(String[] args) {
        MyPreparedStatement.doStatement();

        BatchInsert.doInsert();

        ParametrizedSelect.doParameterizedSelect();

        TransactionWithSavePoint.doTransactionWithSavePoint();
    }
}
