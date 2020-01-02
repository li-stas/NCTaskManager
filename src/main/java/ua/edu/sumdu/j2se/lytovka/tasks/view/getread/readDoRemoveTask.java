package ua.edu.sumdu.j2se.lytovka.tasks.view.getread;

public class readDoRemoveTask {
    /**
     * запрос на удаление задания
     * @return
     */
    public int getread(){
        System.out.print("Задание удалить? ");
        return new readYesNo().getnRet();
    }
}
