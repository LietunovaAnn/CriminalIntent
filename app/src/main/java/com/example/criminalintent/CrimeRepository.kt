package com.example.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase
import com.example.criminalintent.database.migration_1_2
import java.io.File
import java.lang.IllegalStateException
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()

    private val crimeDAO = database.crimeDAO()
    private val executor = Executors.newSingleThreadExecutor()
    private val fileDir = context.applicationContext.filesDir

    fun getCrimes(): LiveData<List<Crime>> = crimeDAO.getCrimes()

    fun getCrime(id: UUID): LiveData<Crime?> = crimeDAO.getCrime(id)

    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDAO.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDAO.addCrime(crime)
        }
    }

    fun getPhotoFile(crime: Crime): File = File(fileDir, crime.photoFileName)

    fun getPhotoFileForName(photoFileName: String): File = File(fileDir, photoFileName)

    companion object {
        private var INSTANCE : CrimeRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException ("CrimeRepository must be initialized")
        }
    }
}