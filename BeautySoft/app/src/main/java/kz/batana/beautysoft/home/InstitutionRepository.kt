package kz.batana.beautysoft.home

import kz.batana.beautysoft.local_storage.InstitutionDao

class InstitutionRepository(
        val service: HomeService,
        val institutionDao: InstitutionDao)
    : HomeContract.HomeRepository {


}