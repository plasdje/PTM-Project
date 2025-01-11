package Server;
import java.io.BufferedReader;
import java.util.Map;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class RequestParser {

    public static RequestInfo parseRequest(BufferedReader reader) throws IOException {
        RequestInfo requestInfo = null;
        Scanner scanner = new Scanner(reader);
        String firstLine = scanner.nextLine();
        String command = firstLine.split(" ")[0];
        String uri = firstLine.split(" ")[1];
        String buff = uri;
        int length = buff.split("/").length;
        String[] segments = new String[length - 1];
        for(int i = 1; i < length; i++){
            segments[i - 1] = buff.split("/")[i];
        }
        segments[length - 2] = segments[length - 2].split("\\?")[0];
        String[] parameters = uri.split("\\?")[1].split("&");
        int pLength = parameters.length;
        Map<String,String> mParametrs = new HashMap<>();
        for(int i = 0; i < pLength; i++){
            String key = parameters[i].split("=")[0];
            String value = parameters[i].split("=")[1];
            mParametrs.put(key, value);
        }
        String line;
        while(!scanner.nextLine().isEmpty());
        while ((line = scanner.nextLine()) != null && !line.isEmpty()) {
            String key = line.split("=")[0];
            String value = line.split("=")[1];
            mParametrs.put(key,value);
        }
        byte[] bString = null;
        String contentLine = scanner.nextLine() + "\n";
        bString = contentLine.getBytes();


        requestInfo = new RequestInfo(command, uri, segments, mParametrs, bString);
        scanner.close();
        return requestInfo;
    }

	// RequestInfo given internal class
    public static class RequestInfo {
        private final String httpCommand;
        private final String uri;
        private final String[] uriSegments;
        private final Map<String, String> parameters;
        private final byte[] content;

        public RequestInfo(String httpCommand, String uri, String[] uriSegments, Map<String, String> parameters, byte[] content) {
            this.httpCommand = httpCommand;
            this.uri = uri;
            this.uriSegments = uriSegments;
            this.parameters = parameters;
            this.content = content;
        }

        public String getHttpCommand() {
            return httpCommand;
        }

        public String getUri() {
            return uri;
        }

        public String[] getUriSegments() {
            return uriSegments;
        }

        public Map<String, String> getParameters() {
            return parameters;
        }

        public byte[] getContent() {
            return content;
        }
    }
}
