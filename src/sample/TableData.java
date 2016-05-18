package sample;

/**
 * Created by ZloiY on 17.05.2016.
 */
public class TableData {
    private String nameFile;
    private String endFile;
    private Long size;
    private String lastChangeFile;

    TableData(){}

    TableData(String name, String end, Long size, String last){
        nameFile = name;
        endFile = end;
        this.size = size;
        lastChangeFile = last;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getEndFile() {
        return endFile;
    }

    public void setEndFile(String endFile) {
        this.endFile = endFile;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getLastChangeFile() {
        return lastChangeFile;
    }

    public void setLastChangeFile(String lastChangeFile) {
        this.lastChangeFile = lastChangeFile;
    }
}
