package com.example.criminalintent

import androidx.lifecycle.ViewModel
//CrimeListViewModel представляет собой централизoванное хранилище для объектов Crime
class CrimeListViewModel: ViewModel() {

  private val crimeRepository = CrimeRepository.get()

  val crimeListLiveData = crimeRepository.getCrimes()

  fun addCrime(crime: Crime) {
    crimeRepository.addCrime(crime)
  }
}