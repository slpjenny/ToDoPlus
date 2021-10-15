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

![스크린샷 2021-10-15 오후 3.49.10.png](README%20MD%20bb5f0a1972f34d938d32b8f631a4778a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2021-10-15_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.49.10.png)

![스크린샷 2021-10-15 오후 3.50.16.png](README%20MD%20bb5f0a1972f34d938d32b8f631a4778a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2021-10-15_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.50.16.png)

1. 하루 성취율 달력에서 확인할 수 있음. (메모 추가 가능) 
    
    **`(구현 실패)`**
    
    - 날짜를 클릭할시, 메모와 성취율을 보여준다.
    - 메모가 추가된 날짜에는 하단에 점 아이콘이 생성된다.
    
    **`(구현 성공)`**
    
    - 메모가 DB에 저장된다.
    - 커스텀 달력 제작

![스크린샷 2021-10-15 오후 3.50.53.png](README%20MD%20bb5f0a1972f34d938d32b8f631a4778a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2021-10-15_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.50.53.png)

![스크린샷 2021-10-15 오후 3.51.46.png](README%20MD%20bb5f0a1972f34d938d32b8f631a4778a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2021-10-15_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.51.46.png)

![스크린샷 2021-10-15 오후 3.51.34.png](README%20MD%20bb5f0a1972f34d938d32b8f631a4778a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2021-10-15_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.51.34.png)

1. 루틴만 모아서 보여주기 가능
    
    `(구현 실패)` 
    
    - 요일 체크가 되지 않음
    
    `(구현 성공)`
    
    - 루틴들만 리스트로 보여줄 수 있음.

![스크린샷 2021-10-15 오후 3.52.53.png](README%20MD%20bb5f0a1972f34d938d32b8f631a4778a/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2021-10-15_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_3.52.53.png)

### Reference

---

- TickTIck:To-do

[https://play.google.com/store/apps/details?id=com.ticktick.task](https://play.google.com/store/apps/details?id=com.ticktick.task)

- To-Do List – 스케줄 플래너 & 리마인더

[https://play.google.com/store/apps/details?id=todolist.scheduleplanner.dailyplanner.todo.reminders](https://play.google.com/store/apps/details?id=todolist.scheduleplanner.dailyplanner.todo.reminders)

- Microsoft To Do: 목록, 작업 및 미리 알림

[https://play.google.com/store/apps/details?id=com.microsoft.todos](https://play.google.com/store/apps/details?id=com.microsoft.todos)

