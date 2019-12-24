package part3.lesson15.task01;

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
