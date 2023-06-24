package DbContext.interfaces;

import java.util.List;

import DbContext.models.GameRecord;

public interface IDatabaseContext {
    List<GameRecord> getTopGameRecords();
    boolean AddGameRecord(GameRecord record);
}
