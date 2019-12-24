package part4.lesson16.task01;

import part4.lesson16.task01.funcClasses.BatchInsert;
import part4.lesson16.task01.funcClasses.MyPreparedStatement;
import part4.lesson16.task01.funcClasses.ParametrizedSelect;
import part4.lesson16.task01.funcClasses.TransactionWithSavePoint;

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
