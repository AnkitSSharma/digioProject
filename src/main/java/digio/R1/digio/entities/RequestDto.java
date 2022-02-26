package digio.R1.digio.entities;

import java.util.Arrays;

public class RequestDto {

    public enum displayOnPage{first, last, all, custom};
    SigneeDto[] signeeDtos;
    String expireInDays;
    displayOnPage dop;
    String fileName;
    String fileData;

    @Override
    public String toString() {
        return "RequestDto{" +
                "signeeDtos=" + Arrays.toString(signeeDtos) +
                ", expireInDays='" + expireInDays + '\'' +
                ", dop=" + dop +
                ", fileName='" + fileName + '\'' +
                ", fileData='" + fileData + '\'' +
                '}';
    }
}
