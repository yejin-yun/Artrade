1. WebContent밑의 index.jsp 파일을 실행시킨다.

2. 메인 페이지에서 삼선짜리의 메뉴바를 열어 로그인을 선택한다. 

3. 로그인 페이지에서 회원가입을 시작한다. 

4. 회원가입 창에서 아이디를 입력하고 중복확인을 누른다. 그리고 나머지 값들을 입력하는데  입력 칸이 비어있거나 전화번호나 이메일의 경우 형식에 맞지 않으면 경고창이 뜬다.
 또한 상품이 하나도 체크되지 않은 상태에서 구매버튼을 눌러도 경고창이 뜬다. 전화번호의 경우 번호만 입력해도 자동으로 -가 들어가게 설정해두었다. reset 버튼을 누르면 입력한 값이 다 reset된다. 
 
5. 회원 가입을 완료하면 로그인 페이지로 이동되는데 방금 회원가입한 계정으로 로그인을 한다. 로그인에 성공하면 메인 페이지로 온다. 

6. 작품 메뉴에서 작품 보기를 선택한다. 작품에 있는 하트를 클릭하면 해당 작품이 위시리스트에 담기고, 색이 검은색으로 변한다. 만약 위시에 담겨서 검은색일때 하트를 누른다면 다시 하얀색이 된다. 작품 자체를 클릭하면 작품의 상세 페이지로 이동한다.

7. 작품의  상세 페이지에서 주문을 누르면 해당 작품을 바로 구매할 수 있다.이때 로그인이 되어 있지 않다면 로그인 페이지로 이동한다. 
작품의 상세 페이지에서 장바구니 담기를 누르면 장바구니에 담기고 장바구니(cart) 페이지로 이동한다. 이때 이미 장바구니에 담겨 있는 작품이라면 경고창이 뜨고 장바구니에 담기지 않는다. 
작품의 상세 페이지에서 위시리스트에 담기를 누르면 위시리스트에 담기고 작품 보기 페이지로 돌아간다. 만약 이미 위시리스트에 있던 작품이라면 경고창이 뜨고 위시리스트에는 포함되지 않는다.
 
8. 작품의 상세페이지에 있는 주문 버튼을 누르면 주문 페이지가 나온다. 주문 상품 보기 버튼을 누르면 고른 상품을 확인할 수 있고, 고른 상품을 누르면 해당 상품의 상세 페이지로 이동한다. 
배송지, 수취자, 전화번호, 무통장 입금의 입금자명과 입금은행, 실시간 계좌이체의 예금주 명을 입력하지 않으면 경고창이 뜬다. 전화번호의 경우 번호만 입력해도 자동으로 '-'가 들어가게 설정해두었다. 

9. 주문이 완료되었다면 주문 조회 페이지로 이동한다. 주문 조회 페이지에서 '구매내역보기'버튼을 누르면 해당 주문에서 구매한 상품들을 내역을 확인할 수 있다. 
구매내역의 상품을 클릭하면 상품의 상세페이지로 이동하고, 위의 back버튼을 누르면 이전 페이지로 이동한다.

10. 작품 페이지 혹은 작품 상세페이지를 통해서 5개 정도의 작품을 위시리스트에 넣고,5개 외의 다른 작품 2개 정도를 카트에 넣는다. 
카트에 넣을 때는 작품 상세페이지에 있는 버튼을 이용하여 넣으면 된다. 

11. 이제 메뉴바를 열고 cart 페이지로 이동한다. 카트에 담은 상품이 있는 것을 확인할 수 있다.

12. 메뉴바를 열고 위시리스트 페이지로 이동한다. 위시에 담은 상품들이 있는 것을 확인할 수  있다.
3개 정도의 작품을 작품 위에 있는 체크 박스를 눌러 개별적으로 삭제시켜 준다. 이때 체크박스 선택 없이 삭제하고자 하면 경고창이 뜬다. 
선택 상품 모두 삭제나 선택 상품 장바구니 이동 또한 체크 박스에 선택된 것 없이 클릭할 경우 경고창이 뜬다. 
그 다음에  맨 아래의 전체 선택 버튼을 누르고, 선택 상품 장바구니 이동 버튼을 누른다. 그럼 장바구니 페이지로 이동한다.

13. 이동한 장바구니 페이지에서 선택한 작품들이 장바구니 페이지에 있음을 확인할 수 있다. 이 중 2개의 상품을 골라 결제 버튼을 누른다. 
체크 박스 선택 없이 결제 버튼을 누르면 경고창이 뜬다. 이후의 과정은 8~9번 과정과 동일하다. 

14. 작품 메뉴에 커서를 두면 '등록하기'메뉴가 있는데, 이를 선택한다. 

15. 작품등록 페이지에서 찾아보기 버튼을 통해 등록할 작품의 사진을 업로드한다. 작품명, 작가명, 작품크기, 키워드, 상품설명, 가격이 모두 입력되어야 정상적으로 등록된다. 
작품의 크기의 예로는 B4, A4, A3... 등이 있다. 가격은 0이상의 정수값만 입력이 가능하다. 

16. 메뉴바 옆의 돋보기를 누르면 제일 위 상단에 검색창이 나오는데 거기에 '피카소'라고 입력한 뒤 키보드 엔터를 누른다.(검색을 실행시킬 수 있는 버튼이 없기 때문에 enter를 쳐야 한다.)
그럼 검색의 실행결과로 피카소의 작품과 피카소 전시회가 나오는 것을 확인할 수  있다. 

17. 전시회 메뉴를 선택하거나 메인 페이지 하단의 사진에 마우스를 가져다 대면 '전시회 페이지 가기'가 뜨는데 두 경로를 통해 전시회 페이지로 이동할 수 있다. 

18. 메인페이지 하단의 경로를 이용하여 전시회 페이지에 들어왔다. 양 옆의 화살표 버튼을 누르면 다른 전시회를 볼 수 있다. 
모네 전시회 사진(초록색)을 누르면 모달이 뜨는데 이를 통해 전시회에 입장하거나 입장권을 구매할 수 있다. 입장권이 없는데 입장 버튼을 누르면 전시회페이지로 돌아온다.
입장권 구매 버튼을 누르면 입장권 구매 페이지가 나오는데, 주문 상품보기를 통해 구매할 전시회를 알 수 있다. 입금 수단을 선택한 다음에 구매버튼을 누른다.

19. 구매한 모네 전시회의 사진을 눌러 모달을 띄운 뒤 입장을  누른다. 그러면 해당 전시회에서 전시하는 작품들을 볼 수 있다. 
작품을 클릭하면 작품 상세페이지로 이동한다. 작품 상세 페이지에서 하단의 되돌아가기 버튼을 통해 이전 페이지로 돌아갈 수 있다.

20. 이번에 메뉴의 전시회를 눌러 전시회 페이지에 들어가본다. 밑에 전시 목록 검색 창이 있다. 해당 창에 '피카소'라고 입력을 한다. 
그러면 위 상단의 돋보기 검색창을 이용한 것과 다르게 전시회와 관련된 것만 검색이 된다. 이 검색된 전시회를 누르면 아까와 같이 모달이 나오고 입장 및 입장권 구매 버튼이 있다. 

21. 메뉴바를 눌러 Mypage로 이동한다. 주문조회 와 위시리스트는 각각 주문 조회 페이지와 위시리스트 페이지로 들어가는데, 위에서 봤던 페이지와 동일하다.    
회원정보 수정 페이지로 들어간다. ID는 수정을 못하게 되어있고 나머지는 수정이 가능하다. 
이름을 '고길동'으로 바꾸고, 전화번호를 02-940-1111로 바꾼뒤 password 재확인 값을 입력한 뒤 확인 버튼을 누르면 바뀐 정보로 수정 페이지가 뜬다. 
이때 전화번호는 자동으로 '-'가 입력되어 수동으로'-' 입력하는 것은 적용되지 않는다. 
수정 페이지에서도 회원가입페이지와 동일하게 입력 칸이 비어있거나 전화번호나 이메일의 경우 형식에 맞지 않으면 경고창이 뜬다.

22. 다시 메뉴바를 눌러 Mypage로 이동한 뒤 탈퇴하기를 누른다. 해당 계정의 비밀번호를 입력한 뒤 확인을 누르면 탈퇴가 완료되고, 메인 페이지로 이동한다. 
이때 비밀번호가 틀릴 경우 경고창이 뜨며, 탈퇴는 진행되지 않는다.  
 