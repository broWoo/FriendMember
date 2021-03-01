package choi.java.friend;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class FriendManager extends Frame implements ActionListener, KeyListener, ItemListener {
   private static final long serialVersionUID = 1L;
  
  //속성
  private TextField nametf = new TextField(20);
  
  private TextField jumin1tf = new TextField(6);
  
  private TextField jumin2tf = new TextField(7);
  
  private Choice telch = new Choice();
  
  private TextField tel1tf = new TextField(4);
  
  private TextField tel2tf = new TextField(4);
  
  private CheckboxGroup cg = new CheckboxGroup();
  
  private Checkbox man = new Checkbox("남성", true, this.cg);
  
  private Checkbox woman = new Checkbox("여성", this.cg, false);
  
  private String[] hStr = new String[] { "독서", "영화", "음악", "게임", "쇼핑"};
  
  private Checkbox[] hobby = new Checkbox[this.hStr.length];
  
  private Button addbt = new Button("등록");
  
  private Button dispbt = new Button("분석");
  
  private Button updatebt = new Button("수정");
  
  private Button delbt = new Button("삭제");
  
  private Button clearbt = new Button("클리어");
  
  private List listli = new List();
  
  private TextArea infota = new TextArea();
  
  private MenuBar mb = new MenuBar();
  
  private Menu mfile = new Menu("File");
  
  private MenuItem mfnew = new MenuItem("New File");
  
  private MenuItem mfopen = new MenuItem("File Open...");
  
  private MenuItem mfsave = new MenuItem("File Save...");
  
  private MenuItem mfsaveas = new MenuItem("File Save As");
  
  private MenuItem mfexit = new MenuItem("Exit");
  
  private Menu mhelp = new Menu("Help");
  
  private MenuItem mhversion = new MenuItem("Version Info");
  
  private Dialog version = new Dialog(this, "Program Version", false);
  
  private Label label = new Label("Friend Manager Version 1.0", 1);
  
  private Button dbt = new Button("확인");
  
  private ArrayList<Friend> data = new ArrayList<Friend>();
  
  
  // 생성자
  public FriendManager() {
    super("Friend Manager");
    setDialog();
    setMenu();
    setForm();
    setEvent();
    setFile();
    setVisible(true);
  }
  
  public class Friend {
      private String name;
      private String jumin;
      private String phone;
      private String gen;
      private String hobby;
      
      public Friend() {}
      
      public Friend(String name, String jumin, String phone, String gen, String hobby){
         this.name = name;
         this.jumin = jumin;
         this.phone = phone;
         this.gen = gen;
         this.hobby = hobby;
      }
      
      
      public String getName() {
         return name;
      }
      public void setName(String name) {
         this.name = name;
      }
      public String getJumin() {
         return jumin;
      }
      public void setJumin(String jumin) {
         this.jumin = jumin;
      }
      public String getPhone() {
         return phone;
      }
      public void setPhone(String phone) {
         this.phone = phone;
      }
      public String getGen() {
         return gen;
      }
      public void setGen(String gen) {
         this.gen = gen;
      }
      public String getHobby() {
         return hobby;
      }
      public void setHobby(String hobby) {
         this.hobby = hobby;
      }
      @Override
      public String toString() {
         return this.name;
      }
   }
  
  
  // 메소드 
  public void setFile() {
    
  }
  public void actionPerformed(ActionEvent paramActionEvent) {
    if (paramActionEvent.getSource() == this.nametf) {
      this.jumin1tf.requestFocus();
      return;
    } 
    if (paramActionEvent.getSource() == this.tel1tf) {
      this.tel2tf.requestFocus();
      return;
    } 
    if (paramActionEvent.getSource() == this.addbt) {
      if (!inputCheck()) {
        this.infota.setText("\t입력오류 ");
        clearForm();
        return;
      } 
      String str1 = this.nametf.getText().trim();
      String str2 = this.jumin1tf.getText().trim() + this.jumin2tf.getText().trim();
      String str3 = this.telch.getSelectedItem().trim() + "-" + this.tel1tf.getText().trim() + "-" + this.tel2tf.getText().trim();
      String str4 = this.man.getState() ? "남성":"여성";
      String str5 = "";
      for (byte b = 0; b < this.hobby.length; b++) {
        if (this.hobby[b].getState())
          str5 = str5 + this.hobby[b].getLabel() + "-"; 
      } 
      if (str5.length() == 0) {
        str5 = "없음";
      } else {
        str5 = str5.substring(0, str5.length() - 1);
      } 
      Friend friend = new Friend(str1, str2, str3, str4, str5);
      this.data.add(friend);
      this.listli.add(friend.toString());
      this.infota.setText("\n\t성공적으로 등록 되었습니다");
      clearForm();
      return;
    } 
    if (paramActionEvent.getSource() == this.dispbt) {
      int i = this.listli.getSelectedIndex();
      Friend friend = this.data.get(i);
      String str1 = friend.getJumin();
      String str2 = friend.getName();
      if (!checkJumin(str1)) {
        this.infota.setText("\n\t잘못된 주민번호 입니다");
        this.infota.append("\n\t주민번호를 수정해주세요");
        return;
      } 
      display(str2, str1);
      return;
    } 
    if (paramActionEvent.getSource() == this.updatebt) {
      String str1 = this.telch.getSelectedItem().trim() + "-" + this.tel1tf.getText().trim() + "-" + this.tel2tf.getText().trim();
      String str2 = "";
      int i;
      for (i = 0; i < this.hobby.length; i++) {
        if (this.hobby[i].getState())
          str2 = str2 + this.hobby[i].getLabel() + "-"; 
      } 
      if (str2.length() == 0) {
        str2 = "없음";
      } else {
        str2 = str2.substring(0, str2.length() - 1);
      } 
      i = this.listli.getSelectedIndex();
      Friend friend = this.data.get(i);
      friend.setPhone(str1);
      friend.setHobby(str2);
      this.infota.setText("\n\t성공적으로 수정되었습니다");
      return;
    } 
    if (paramActionEvent.getSource() == this.delbt) {
      int i = this.listli.getSelectedIndex();
      this.data.remove(i);
      this.listli.remove(i);
      buttonActive(true);
      formActive(true);
      this.infota.setText("\n\t성공적으로 삭제되었습니다");
      clearForm();
      return;
    } 
    if (paramActionEvent.getSource() == this.clearbt) {
      this.infota.setText("");
      buttonActive(true);
      formActive(true);
      clearForm();
      return;
    } 
    if (paramActionEvent.getSource() == this.mfexit)
      System.exit(0); 
    if (paramActionEvent.getSource() == this.mhversion) {
      Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension dimension2 = this.version.getSize();
      this.version.setLocation(dimension1.width / 2 - dimension2.width / 2, dimension1.height / 2 - dimension2.height / 2);
      this.version.setVisible(true);
    } 
    if (paramActionEvent.getSource() == this.dbt)
      this.version.setVisible(false); 
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent) {
    if (paramItemEvent.getSource() == this.telch) {
      this.tel1tf.requestFocus();
      return;
    } 
    if (paramItemEvent.getSource() == this.listli) {
      int i = this.listli.getSelectedIndex();
      Friend friend = this.data.get(i);
      this.nametf.setText(friend.getName());
      this.jumin1tf.setText(friend.getJumin().substring(0, 6));
      this.jumin2tf.setText(friend.getJumin().substring(6));
      StringTokenizer stringTokenizer = new StringTokenizer(friend.getPhone(), "-");
      String str1 = stringTokenizer.nextToken();
      String str2 = stringTokenizer.nextToken();
      String str3 = stringTokenizer.nextToken();
      this.telch.select(str1);
      this.tel1tf.setText(str2);
      this.tel2tf.setText(str3);
      if (friend.getGen().equals("남성")) {
        this.man.setState(true);
      } else {
        this.woman.setState(true);
      } 
      for (byte b = 0; b < this.hobby.length; b++)
        this.hobby[b].setState(false); 
      stringTokenizer = new StringTokenizer(friend.getHobby(), "-");
      while (stringTokenizer.hasMoreTokens()) {
        String str = stringTokenizer.nextToken();
        for (byte b1 = 0; b1 < this.hobby.length; b1++) {
          if (str.equals(this.hobby[b1].getLabel()))
            this.hobby[b1].setState(true); 
        } 
      } 
      buttonActive(false);
      formActive(false);
    } 
  }
  
  public void keyReleased(KeyEvent paramKeyEvent) {  //누른 키 값 확인.
    if (paramKeyEvent.getSource() == this.jumin1tf && 
      this.jumin1tf.getText().trim().length() == 6) {
      this.jumin2tf.requestFocus();
      return;
    } 
    if (paramKeyEvent.getSource() == this.jumin2tf && 
      this.jumin2tf.getText().trim().length() == 7) {
      this.telch.requestFocus();
      return;
    } 
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {}
  
  public void keyTyped(KeyEvent paramKeyEvent) {}
  
  public void setEvent() {
    this.dbt.addActionListener(this);
    this.mhversion.addActionListener(this);
    this.mfexit.addActionListener(this);
    this.clearbt.addActionListener(this);
    this.delbt.addActionListener(this);
    this.updatebt.addActionListener(this);
    this.dispbt.addActionListener(this);
    this.listli.addItemListener(this);
    this.addbt.addActionListener(this);
    this.tel1tf.addActionListener(this);
    this.telch.addItemListener(this);
    this.jumin2tf.addKeyListener(this);
    this.jumin1tf.addKeyListener(this);
    this.nametf.addActionListener(this);
    addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent param1WindowEvent) {
            System.exit(0);
          }
        });
  }
  
  public void display(String paramString1, String paramString2) {
    int[] arrayOfInt = new int[paramString2.length()];
    int i;
    for (i = 0; i < arrayOfInt.length; i++)
      arrayOfInt[i] = paramString2.charAt(i) - 48; 
    i = 1900;
    switch (arrayOfInt[6]) {
      case 0:
      case 9:
        i = 1800;
        break;
      case 3:
      case 4:
        i = 2000;
        break;
    } 
    i = i + arrayOfInt[0] * 10 + arrayOfInt[1];
    int j = arrayOfInt[2] * 10 + arrayOfInt[3];
    int k = arrayOfInt[4] * 10 + arrayOfInt[5];
    String str = "";
    switch (arrayOfInt[7]) {
      case 0:
        str = "서울";
        break;
      case 1:
        str = "경기";
        break;
      case 2:
        str = "강원";
        break;
      case 3:
        str = "충북";
        break;
      case 4:
        str = "충남";
        break;
      case 5:
        str = "전북";
        break;
      case 6:
        str = "전남";
        break;
      case 7:
        str = "경북";
        break;
      case 8:
        str = "경남";
        break;
      case 9:
        str = "제주";
        break;
    } 
    int m = Calendar.getInstance().get(1);
    this.infota.setText("\n이름 :\t" + paramString1 + "\n");
    this.infota.append("\n생년월일 :\t" + i + "" + j + "" + k + "\n");
    this.infota.append("\n나이 :\t" + (m - i + 1) + "\n");
    this.infota.append("\n성별 :\t" + ((arrayOfInt[6] % 2 == 0) ? "여성\n" : "남성\n"));
    this.infota.append("\n출생지역 :\t" + str + "\n");
  }
  
  public boolean checkJumin(String paramString) {
    int[] arrayOfInt = new int[13];
    if (paramString.length() != 13)
      return false; 
    for (byte b1 = 0; b1 < paramString.length(); b1++)
      arrayOfInt[b1] = paramString.charAt(b1) - 48; 
    float f1 = 0.0F, f2 = 0.0F, f3 = 0.0F, f4 = 2.0F;
    for (byte b2 = 0; b2 < arrayOfInt.length - 1; b2++) {
      if (f4 == 10.0F)
        f4 = 2.0F; 
      f1 += arrayOfInt[b2] * f4;
      f4++;
    } 
    f2 = 11.0F * (int)(f1 / 11.0F) + 11.0F - f1;
    f3 = f2 - 10.0F * (int)(f2 / 10.0F);
    if (f3 != arrayOfInt[arrayOfInt.length - 1])
      return false; 
    return true;
  }
  
  public void formActive(boolean paramBoolean) {
    this.nametf.setEnabled(paramBoolean);
    this.jumin1tf.setEnabled(paramBoolean);
    this.jumin2tf.setEnabled(paramBoolean);
    this.man.setEnabled(paramBoolean);
    this.woman.setEnabled(paramBoolean);
  }
  
  public void buttonActive(boolean paramBoolean) {
    this.addbt.setEnabled(paramBoolean);
    this.dispbt.setEnabled(!paramBoolean);
    this.updatebt.setEnabled(!paramBoolean);
    this.delbt.setEnabled(!paramBoolean);
    this.clearbt.setEnabled(!paramBoolean);
  }
  
  public void clearForm() {
    this.nametf.setText("");
    this.jumin1tf.setText("");
    this.jumin2tf.setText("");
    this.telch.select(0);
    this.tel1tf.setText("");
    this.tel2tf.setText("");
    this.man.setState(true);
    for (byte b = 0; b < this.hobby.length; b++)
      this.hobby[b].setState(false); 
    this.nametf.requestFocus();
  }
  
  public boolean inputCheck() {
    if (this.nametf.getText().trim().length() == 0)
      return false; 
    if (this.jumin1tf.getText().trim().length() != 6)
      return false; 
    if (this.jumin2tf.getText().trim().length() != 7)
      return false; 
    if (this.tel1tf.getText().trim().length() == 0)
      return false; 
    if (this.tel2tf.getText().trim().length() == 0)
      return false; 
    return true;
  }
  
  public void setForm() {
    setLayout(new BorderLayout());
    add("North", new Label());
    add("South", new Label());
    add("West", new Label());
    add("East", new Label());
    Panel panel1 = new Panel(new BorderLayout());
    Panel panel2 = new Panel(new BorderLayout());
    panel2.add("North", new Label("전 체 목 록", 1));
    panel2.add("Center", this.listli);
    panel2.add("West", new Label());
    panel1.add("East", panel2);
    Panel panel3 = new Panel(new BorderLayout());
    panel3.add("North", new Label("개 인 정 보 분 석", 1));
    panel3.add("Center", this.infota);
    panel1.add("South", panel3);
    Panel panel4 = new Panel(new BorderLayout());
    panel4.add("North", new Label("개인정보입력", 1));
    Panel panel5 = new Panel(new GridLayout(5, 1, 3, 3));
    panel5.add(new Label("이      름 : ", 2));
    panel5.add(new Label("주민번호 : ", 2));
    panel5.add(new Label("전화번호 : ", 2));
    panel5.add(new Label("성      별 : ", 2));
    panel5.add(new Label("취      미 : ", 2));
    panel4.add("West", panel5);
    Panel panel6 = new Panel(new GridLayout(5, 1, 3, 3));
    Panel panel7 = new Panel(new FlowLayout(0));
    panel7.add(this.nametf);
    panel6.add(panel7);
    Panel panel8 = new Panel(new FlowLayout(0));
    panel8.add(this.jumin1tf);
    panel8.add(new Label("-", 1));
    panel8.add(this.jumin2tf);
    panel6.add(panel8);
    Panel panel9 = new Panel(new FlowLayout(0));
    makeTel();
    panel9.add(this.telch);
    panel9.add(new Label("-", 1));
    panel9.add(this.tel1tf);
    panel9.add(new Label("-", 1));
    panel9.add(this.tel2tf);
    panel6.add(panel9);
    Panel panel10 = new Panel(new FlowLayout(0));
    panel10.add(this.man);
    panel10.add(this.woman);
    panel6.add(panel10);
    Panel panel11 = new Panel(new FlowLayout(0));
    for (byte b = 0; b < this.hStr.length; b++) {
      this.hobby[b] = new Checkbox(this.hStr[b]);
      panel11.add(this.hobby[b]);
    } 
    panel6.add(panel11);
    panel4.add("Center", panel6);
    Panel panel12 = new Panel(new FlowLayout(1));
    panel12.add(this.addbt);
    panel12.add(this.dispbt);
    panel12.add(this.updatebt);
    panel12.add(this.delbt);
    panel12.add(this.clearbt);
    panel4.add("South", panel12);
    panel1.add("Center", panel4);
    add(panel1);
    pack();
    Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimension2 = getSize();
    setLocation(dimension1.width / 2 - dimension2.width / 2, dimension1.height / 2 - dimension2.height / 2);
    buttonActive(true);
    this.nametf.requestFocus();
  }
  
  public void makeTel() {
    String[] arrayOfString = { "010", "011", "016", "019" };
    for (String str : arrayOfString)
      this.telch.add(str); 
  }
  
  public void setMenu() {
    setMenuBar(this.mb);
    this.mb.add(this.mfile);
    this.mfile.add(this.mfnew);
    this.mfile.addSeparator();
    this.mfile.add(this.mfopen);
    this.mfile.add(this.mfsave);
    this.mfile.add(this.mfsaveas);
    this.mfile.addSeparator();
    this.mfile.add(this.mfexit);
    this.mb.add(this.mhelp);
    this.mhelp.add(this.mhversion);
  }
  
  public void setDialog() {
    this.version.setLayout(new BorderLayout());
    this.label.setFont(new Font("굴림체", 1, 12));
    this.version.add("Center", this.label);
    this.version.add("South", this.dbt);
    this.version.setSize(200, 150);
  }
  
  public static void main(String[] paramArrayOfString) {
    new FriendManager();
  }
}