package com.example.notesapp.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
class Notes (

    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "sub_title") var subTitle:String?=null,
    @ColumnInfo(name = "date_time") var dateTime:String? = null,
    @ColumnInfo(name = "note_text") var noteText:String,
    @ColumnInfo(name = "img_path") var imgPath:String? = null,
    @ColumnInfo(name = "web_link") var webLink:String? = null,
    @ColumnInfo(name = "color") var color:String? = null,
    @PrimaryKey(autoGenerate = true) var id:Int=0
):Parcelable
{
    override fun toString(): String {
        return "$title : $dateTime"
    }
}


