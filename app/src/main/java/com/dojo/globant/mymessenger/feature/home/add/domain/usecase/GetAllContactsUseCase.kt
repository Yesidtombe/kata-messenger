package com.dojo.globant.mymessenger.feature.home.add.domain.usecase

import com.dojo.globant.mymessenger.feature.home.add.data.repository.GetAllContactsRepository

class GetAllContactsUseCase(
    private val repository: GetAllContactsRepository
) {
    suspend fun getAllPhoneContacts() = repository.getPhoneContacts()

    suspend fun getAllContactNumbers() = repository.getContactNumbers()

}