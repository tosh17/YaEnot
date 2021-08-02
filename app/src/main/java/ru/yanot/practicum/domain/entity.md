### Profession

id: Long, 

title: String,

durationMonths: Int,

durationHours: Int,

imageUrl: String 



### Course

id: Long, 

title: String,

available: Boolean, 

duration: Int



### User

id: Long,

name: String,

surname: String,

avatarUrl: String



### Topic

id: Long,

imageId: String,

steps: List<Step>



### Lesson

id: Long



### Step (обрезанный Lesson)

id: Long, 

title: String,

state: StepState



### enum StepStape

IN_PROGRESS, PASSED, CLOSE