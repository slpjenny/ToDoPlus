# ToDoPlus

<details>
<summary><bold> Index </bold></summary>
<div markdown="1">

1. [About the project](#about-the-project)
2. [Function](#function)
3. [Reference](#reference)

</div>
</details> 

### About the project

---

- Android ToDo&Routine List Project
- JAVA,Android Studio, SQLite 사용
- 투두 리스트와 루틴 트래커를 합쳐 보다 편리한 일정관리를 할 수 있도록 제작

### Function

---

1. '할일'과 '루틴'을 한번에 관리할 수 있음
    - 아이템 밀어서 삭제 가능
    - 아이템 길게 눌러서 순서 변경 가능
    - 모든 데이터 SQLite 에 저장

<img width="300" alt="스크린샷 2021-10-15 오후 4 22 33" src="https://user-images.githubusercontent.com/68774371/137448211-b2a0e270-8109-4a3c-b892-db1c3cda65e6.png"><img width="300" alt="스크린샷 2021-10-15 오후 3 50 16" src="https://user-images.githubusercontent.com/68774371/137448351-a478466c-9b17-4f22-ac9d-4e05c6f85ef1.png"><br/><br/>


2. 하루 성취율 달력에서 확인할 수 있음. (메모 추가 가능) 
    
    **`(구현 실패)`**
    
    - 날짜를 클릭할시, 메모와 성취율을 보여준다.
    - 메모가 추가된 날짜에는 하단에 점 아이콘이 생성된다.
    
    **`(구현 성공)`**
    
    - 메모가 DB에 저장된다.
    - 커스텀 달력 제작
    
<img width="300" alt="스크린샷 2021-10-15 오후 3 50 53" src="https://user-images.githubusercontent.com/68774371/137448392-d79fe347-b353-41af-8b6e-60d893e5df8f.png"><img width="300" alt="스크린샷 2021-10-15 오후 3 51 46" src="https://user-images.githubusercontent.com/68774371/137448402-a00d7759-d6aa-4af1-86d3-fe43c7297ce6.png">
<img width="300" alt="스크린샷 2021-10-15 오후 3 51 34" src="https://user-images.githubusercontent.com/68774371/137448511-827a4642-72bd-4ee3-992a-da01a00b8211.png">
<br/><br/>

3. 루틴만 모아서 보여주기 가능
    
    `(구현 실패)` 
    
    - 요일 체크가 되지 않음
    
    `(구현 성공)`
    
    - 루틴들만 리스트로 보여줄 수 있음.

<img width="333" alt="스크린샷 2021-10-15 오후 3 52 53" src="https://user-images.githubusercontent.com/68774371/137448524-72a136b2-27da-4db1-bc91-0d8fbc7efe7c.png">


### Reference

---

- TickTIck:To-do

[https://play.google.com/store/apps/details?id=com.ticktick.task](https://play.google.com/store/apps/details?id=com.ticktick.task)

- To-Do List – 스케줄 플래너 & 리마인더

[https://play.google.com/store/apps/details?id=todolist.scheduleplanner.dailyplanner.todo.reminders](https://play.google.com/store/apps/details?id=todolist.scheduleplanner.dailyplanner.todo.reminders)

- Microsoft To Do: 목록, 작업 및 미리 알림

[https://play.google.com/store/apps/details?id=com.microsoft.todos](https://play.google.com/store/apps/details?id=com.microsoft.todos)

