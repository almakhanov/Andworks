package kz.batana.beautysoft.local_storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import kz.batana.beautysoft.core.entity.*
import kz.batana.beautysoft.local_storage.bounds.*

@Database(entities = [
    Customer::class,
    CustomerInstitutionIds::class,
    CustomerMasterIds::class,
    CustomerPhotoId::class,
    CustomerProductIds::class,
    CustomerSessionIds::class,

    Feedback::class,

    Institution::class,
    InstitutionFeedbackIds::class,
    InstitutionMasterIds::class,
    InstitutionPhotoIds::class,
    InstitutionProductIds::class,
    InstitutionSessionIds::class,

    Master::class,
    MasterFeedbackIds::class,
    MasterPhotoIds::class,
    MasterProductIds::class,
    MasterRatingIds::class,
    MasterSessionIds::class,

    Photo::class,

    Product::class,

    Rating::class,

    Session::class,
    SessionPhotoIds::class,
    SessionProductIds::class
], version = 1)
abstract class AppLocalDatabase: RoomDatabase() {
    abstract fun customerDao() : CustomerDao
    abstract fun feedbackDao() : FeedbackDao
    abstract fun institutionDao() : InstitutionDao
    abstract fun masterDao() : MasterDao
    abstract fun photoDao() : PhotoDao
    abstract fun productDao() : ProductDao
    abstract fun ratingDao() : RatingDao
    abstract fun sessionDao() : SessionDao
}