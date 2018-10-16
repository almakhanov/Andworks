package kz.batana.khanproject.entity

import android.arch.persistence.room.*
import io.reactivex.Flowable
import java.util.*

@Entity(tableName = "audio")
data class AudioObject(
        @PrimaryKey(autoGenerate = true)
        var id : Int,
        var name : String,
        var voice : ByteArray

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AudioObject) return false

        if (name != other.name) return false
        if (!Arrays.equals(voice, other.voice)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + Arrays.hashCode(voice)
        return result
    }
}

@Dao
interface AudioDao{
    @Query("SELECT * FROM audio")
    fun getAllAudios() : Flowable<List<AudioObject>>

    @Insert
    fun insertAudio(audio : AudioObject)

}

@Database(entities = [(AudioObject::class)], version = 1)
abstract class AudioDB : RoomDatabase(){
    abstract fun audioDao() : AudioDao
}