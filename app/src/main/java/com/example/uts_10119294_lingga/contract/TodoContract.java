package com.example.uts_10119294_lingga.contract;

public class TodoContract {
    interface View{
        void showMessage(String message);
    }

    interface Interactor{

    }

    interface Presenter{
        void storeTodo();
    }


}
