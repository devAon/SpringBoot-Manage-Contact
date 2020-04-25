# SpringBoot-Manage-Contact

[##_Image|kage@tGKmo/btqDyOvr8A8/9g59NvG1qCgb4K3srNMnrk/img.png|alignRight|data-origin-width="0" data-origin-height="0"|||_##]


Domain

### Mock 객체 만들기
Mock 객체를 만들어서 테스트를 진행할 수 있다. 
Mock객체로 만들고 싶은 객체에 @Mock 어노테이션을 달기만 하면 되는데 이때 만들어진 Mock 객체는 Null이기 때문에 그렇게 하지 않기 위해서 @ExtendWith(MockitoExtension.class)를 추가한다.
