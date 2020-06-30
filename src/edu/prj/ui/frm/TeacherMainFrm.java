package edu.prj.ui.frm;

import javax.swing.*;

import edu.prj.bean.StudentBean;
import edu.prj.service.StudentService;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.frm.paper.PaperListFrm;
import edu.prj.ui.frm.question.QuestionListFrm;
import edu.prj.ui.frm.score.StuScoreListFrm;
import edu.prj.ui.frm.subject.SubjectListFrm;
import edu.prj.ui.frm.teacher.TeacherPwdFrm;

import java.awt.*;
import java.awt.event.*;

public class TeacherMainFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4317031932422472406L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	private JMenuBar menuBar;

	// 系统管理
	private JMenu windows;

	// 权限管理
	private JMenu jurisdiction;
	// 修改密码
	private JMenuItem updatePwd;
	// 注销
	private JMenuItem logout;
	// 退出
	private JMenuItem exit;

	// 试卷管理
	private JMenuItem paperManage;
	// 科目管理
	private JMenuItem subjectManage;
	// 题库管理
	private JMenuItem questionManage;
	// 成绩查询
	private JMenuItem score;

	LoginFrm loginFrm = null;
	String loginName = null;

	private JPanel statusBar;// 状态条
	private JLabel status;// 状态栏

	private void ststusBarInitUI() {
		// 示例前置条件：需要将container的布局由绝对布局null改为BorderLAyout布局，代码如下：
		// container.setLayout(new BorderLayout());

		// 创建状态条面板，将设置为流式布局------从左到右
		statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// statusBar.setBackground(Color.GRAY);
		// 创建状态栏
		status = new JLabel();
		status.setText("状态栏");
		// 将状态栏放到状态条
		statusBar.add(status);

		// 将状态栏放在最底部【BorderLayout的南方SOUTH】
		this.add(statusBar, BorderLayout.SOUTH);
	}

	/**
	 * 无参构造方法
	 */
	public TeacherMainFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();

		ststusBarInitUI();
	}

	/*
	 * 加载背景图片
	 */
	ImageIcon background = new ImageIcon("picture/Three.jpg");
	JLabel label = new JLabel(background);

	/*
	 * 1.初始化用户界面
	 */
	public void initUI() {
		// 定义当前窗体的宽高
		int width = 1000;
		int height = 600;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("教师");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);

		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		// container.setLayout(null);
		container.setLayout(new BorderLayout());
		// 将容器对象添加到当前窗体
		// this.add(container, BorderLayout.CENTER);
		this.add(container);

		// 创建菜单栏对象
		menuBar = new JMenuBar();
		// 将菜单栏对象添加到窗体的菜单栏中
		setJMenuBar(menuBar);

		// 创建系统管理菜单
		windows = new JMenu("系统管理(S)");
		// 设置快捷键Alt+S打开系统管理菜单
		windows.setMnemonic('S');
		// 将系统管理添加到菜单栏对象中
		menuBar.add(windows);

		// 创建修改密码栏
		updatePwd = new JMenuItem("修改密码    Ctrl+X");
		// 设置快捷键Ctrl+X打开修改密码栏
		updatePwd.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
		updatePwd.setMnemonic('X');
		// 将修改密码栏添加到系统管理菜单下
		windows.add(updatePwd);

		// 创建注销栏
		logout = new JMenuItem("注销   Ctrl+Q");
		// 设置快捷键Ctrl+Q打开注销栏
		logout.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));
		logout.setMnemonic('Q');
		// 将注销栏添加到系统管理菜单下
		windows.add(logout);

		// 创建退出栏
		exit = new JMenuItem("退出  Ctrl+E");
		// 设置快捷键Ctrl+E打开退出栏
		exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
		exit.setMnemonic('E');
		// 将退出栏添加到系统菜单下
		windows.add(exit);

		// 创建权限管理菜单
		jurisdiction = new JMenu("权限管理(B)");
		// 设置快捷键Alt+B打开权限管理菜单
		jurisdiction.setMnemonic('B');
		// 将权限管理菜单添加到菜单栏对象中
		menuBar.add(jurisdiction);

		// 创建试卷管理栏
		paperManage = new JMenuItem("试卷管理    Ctrl+P");
		// 设置快捷键Ctrl+P打开试卷管理栏
		paperManage.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
		paperManage.setMnemonic('P');
		// 将试卷管理栏添加到权限管理菜单下
		jurisdiction.add(paperManage);

		// 创管科目管理栏
		subjectManage = new JMenuItem("科目管理    Ctrl+S");
		// 设置快捷键Ctrl+S打开科目管理栏
		subjectManage.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		subjectManage.setMnemonic('S');
		// 将科目管理栏添加到权限管理菜单下
		jurisdiction.add(subjectManage);

		// 创建题库管理栏
		questionManage = new JMenuItem("题库管理    Ctrl+T");
		// 设置快捷键Ctrl+T打开题库管理栏
		questionManage.setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_DOWN_MASK));
		questionManage.setMnemonic('T');
		// 将题库管理栏添加到权限管理菜单下
		jurisdiction.add(questionManage);

		// 创建题库管理栏
		score = new JMenuItem("成绩查询    Ctrl+C");
		// 设置快捷键Ctrl+C打开成绩查询栏
		score.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
		score.setMnemonic('C');
		// 将成绩查询栏添加到权限管理菜单下
		jurisdiction.add(score);

		// 添加背景并设置标签大小
		label.setBounds(0, 0, background.getIconWidth() / 2, background.getIconHeight());
		container.add(label);
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 为当前的窗体添加监听器：目的是重写windowsClosing事件的处理方法
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 这里写定制操作
				window_closing(e);

			}

		});
		// 为试卷管理栏添加事件监听器
		paperManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				paperManage_click(e);
			}

		});
		// 为科目管理栏添加事件监听器
		subjectManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subjectManage_click(e);
			}

		});

		// 为题库管理栏添加事件监听器
		questionManage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				questionManage_click(e);
			}

		});
		// 为修改密码栏添加事件监听器
		updatePwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updatePwd_click(e);
			}
		});
		// 为成绩查询栏添加事件监听器
		score.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				score_click(e);
			}
		});
		// 为注销栏添加事件监听器
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logout_click(e);
			}

		});
		// 为退出栏添加事件监听器
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exit_click(e);
			}

		});
	}

	protected void score_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：查询成绩");
		StuScoreListFrm stuScoreListFrm = new StuScoreListFrm();
		stuScoreListFrm.setVisible(true);
		StudentService studentService = new StudentServiceImpl();
		StudentBean item = studentService.loadByName(loginName);
		if (item == null) {
			return;
		}
		stuScoreListFrm.showListData(item.getName());
		stuScoreListFrm.loginName = loginName;

	}

	private void updatePwd_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("修改密码");
		TeacherPwdFrm teacherPwdFrm = new TeacherPwdFrm();
		teacherPwdFrm.loginName = loginName;
		teacherPwdFrm.setVisible(true);

	}

	// 当点击试卷管理栏时，状态栏会提示：试卷管理
	public void paperManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：试卷管理");
		PaperListFrm frm = new PaperListFrm();
		frm.setVisible(true);
	}

	// 当点击科目管理栏时，状态栏会提示：科目管理
	public void subjectManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：科目管理");
		SubjectListFrm frm = new SubjectListFrm();
		frm.setVisible(true);
	}

	// 当点击班级管理栏时,状态栏会提示:班级管理
	public void questionManage_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：题库管理");
		QuestionListFrm frm = new QuestionListFrm();
		frm.setVisible(true);

	}

	// 当点击注销栏时，状态栏会提示：注销；并会弹出确认注销窗口
	public void logout_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：注销");
		int option = JOptionPane.showConfirmDialog(this, "确定注销系统吗？", "提示", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			if (loginFrm != null) {
				this.dispose();
				loginFrm.setVisible(true);

			}
		}
	}

	// 当点击退出栏时，状态栏会提示：退出；并会弹出确认退出窗口
	public void exit_click(ActionEvent e) {
		// TODO Auto-generated method stub
		status.setText("提示：退出");

		int res = JOptionPane.showConfirmDialog(null, "是否继续？", "提示：即将退出", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION) {
			System.out.println("已退出！");
			System.exit(0);
		} else {
			return;
		}
	}

	// 当点击右上角的关闭按钮会弹出确认退出窗口
	public void window_closing(WindowEvent e) {
		int option = JOptionPane.showConfirmDialog(null, "是否继续？", "提示：即将退出", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			if (e.getWindow() == this) {
				System.exit(0);
			}
		}
	}

	/*
	 * 3.自定义加载
	 */
	public void customLoad() {
	}

	// 窗口标题显示
	public void refreshTitle(String loginName) {
		this.loginName = loginName;
		this.setTitle(this.getTitle() + "- 【" + loginName + "】");
	}

}
