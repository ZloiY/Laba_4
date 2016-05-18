package sample;

/**
 * Created by ZloiY on 17.05.2016.
 */
public class TableData {
    private String nameFIle;
    private String endFile;
    private Long size;
    private String lastChange;

    TableData(){}

    TableData(String name, String end, Long size, String last){
        nameFIle = name;
        endFile = end;
        this.size = size;
        lastChange = last;
    }

    public String getNameFIle() {
        return nameFIle;
    }

    public void setNameFIle(String nameFIle) {
        this.nameFIle = nameFIle;
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

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }
}
