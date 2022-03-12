
package com.js.mystore.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Company(
    @PrimaryKey(autoGenerate = true)
    var companyId: Long = 0,
    var name: String? = null,
    var status: Boolean = true,
    var updateAt: Date? = null,
    var createdAt: Date? = null,
)