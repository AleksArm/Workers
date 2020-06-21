package com.highestaim.workers.data.db

import com.highestaim.workers.data.db.entity.WorkerEntity
import com.highestaim.workers.model.WorkersModel.Data


fun WorkerEntity.toData() = Data(
    name = this.name,
    salary = this.salary,
    age = this.age,
    id = this.id.toString()
)

fun List<WorkerEntity?>.toDataList() = this.map { it?.toData() }

fun Data.toDataEntity() = WorkerEntity(
    name = this.name,
    salary = this.salary,
    age = this.age,
    id = this.id?.toLong()
)

fun List<Data?>.toDataEntityList() = this.map { it?.toDataEntity() }