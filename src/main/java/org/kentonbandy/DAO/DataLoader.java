package org.kentonbandy.DAO;

import java.util.List;
import java.util.Map;

public interface DataLoader {
    Map<String, List<String>> loadGameData();
}
