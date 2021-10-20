package org.kentonbandy.DAO;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DataLoader {
    public Map<String, List<String>> loadGameData();
}
