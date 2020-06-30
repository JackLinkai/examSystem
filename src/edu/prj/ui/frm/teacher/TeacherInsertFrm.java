package edu.prj.ui.frm.teacher;

import com.liuvei.common.SysFun;
import edu.prj.bean.TeacherBean;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeacherInsertFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6002376456669339636L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public TeacherListFrm listFrm = null;

	private JLabel lblLoginName;// 账号的标签
	private JTextField txtLoginName;// 账号的文本框

	private JLabel lblLoginPwd; // 密码的标签
	private JTextField txtLoginPwd;// 密码的文本框

	private JLabel lblStuName;// 姓名的标签
	private JTextField txtStuName;// 姓名的文本框

	private JLabel lblIsDisabled;// 是否禁用的标签
	private ButtonGroup btngrpIsDisabled;// 是否禁用单选按钮
	private JRadioButton rdoIsDisabledStart;// 启用的单选按钮
	private JRadioButton rdoIsDisabledBan;// 禁用的单选按钮

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public TeacherInsertFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	/*
	 * 1.初始化用户界面
	 */
	public void initUI() {
		// 定义当前窗体的宽高
		int width = 400;
		int height = 300;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("系统登录");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);
		customInitUI();
	}

	/*
	 * 2.绑定事件
	 */
	public void bindEvent() {
		// 设置默认的关闭操作，点击右上角的关闭按钮时退出整个应用程序
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// 这里写定制操作
				window_closing(e);
			}
		});
		custBindEvent();
	}

	public void window_closing(WindowEvent e) {
		// 关闭主窗体，显示登陆窗体
		if (listFrm != null) {
			listFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblLoginName = new JLabel();
		lblLoginName.setText("账号:");

		lblLoginName.setBounds(90, 60, 80, 30);
		container.add(lblLoginName);

		txtLoginName = new JTextField();
		txtLoginName.getText();

		txtLoginName.setBounds(140, 60, 160, 30);
		container.add(txtLoginName);

		lblLoginPwd = new JLabel();
		lblLoginPwd.setText("密码:");

		lblLoginPwd.setBounds(90, 65, 100, 100);
		container.add(lblLoginPwd);

		txtLoginPwd = new JTextField();
		txtLoginPwd.getText();

		txtLoginPwd.setBounds(140, 100, 160, 30);
		container.add(txtLoginPwd);

		lblStuName = new JLabel();
		lblStuName.setText("姓名:");

		lblStuName.setBounds(90, 80, 120, 150);
		container.add(lblStuName);

		txtStuName = new JTextField();
		txtStuName.getText();

		txtStuName.setBounds(140, 140, 160, 30);
		container.add(txtStuName);

		lblIsDisabled = new JLabel();
		lblIsDisabled.setText("是否禁用:");

		lblIsDisabled.setBounds(80, 120, 120, 150);
		container.add(lblIsDisabled);

		rdoIsDisabledStart = new JRadioButton("启用");
		rdoIsDisabledStart.setBounds(140, 180, 70, 30);
		container.add(rdoIsDisabledStart);

		rdoIsDisabledBan = new JRadioButton("禁用");
		rdoIsDisabledBan.setBounds(210, 180, 70, 30);
		container.add(rdoIsDisabledBan);
		// 将启用禁用的单选按钮,放到ButtonGroup.作为一组,则只能选中一个
		btngrpIsDisabled = new ButtonGroup();
		btngrpIsDisabled.add(rdoIsDisabledStart);
		btngrpIsDisabled.add(rdoIsDisabledBan);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 230, 70, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 230, 70, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		// lblMsg.setText("提示信息");
		lblMsg.setBounds(70, 280, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);
	}

	private void custBindEvent() {
		// 提交按钮事件处理
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	TeacherService teacherService = new TeacherServiceImpl();

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据
		String LoginName = txtLoginName.getText().trim();// trim忽略前后空格
		String LoginPwd = txtLoginPwd.getText().trim();
		String stuName = txtStuName.getText().trim();

		// 2)为空性判断

		if (SysFun.isNullOrEmpty(LoginName)) {
			lblMsg.setText("提示:账号不能为空!");
			return;
		}
		if (LoginPwd == null || LoginPwd.isEmpty()) {
			lblMsg.setText("提示:密码不能为空!");
			return;
		}
		if (stuName == null || stuName.isEmpty()) {
			lblMsg.setText("提示:姓名不能为空!");
			return;

		}
		if (rdoIsDisabledStart.isSelected() == false && rdoIsDisabledBan.isSelected() == false) {
			lblMsg.setText("提示:请选择是否禁用");
			return;
		}

		Long IsDisabled = 0L;
		if (rdoIsDisabledStart.isSelected()) {
			IsDisabled = 0L;
		} else if (rdoIsDisabledBan.isSelected()) {
			IsDisabled = 1L;
		}

		// 8)真正的添加
		TeacherBean bean = new TeacherBean();
		bean.setLoginName(LoginName);
		bean.setLoginPwd(LoginPwd);
		bean.setName(stuName);
		bean.setIsDisabled(IsDisabled);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = teacherService.insert(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (listFrm != null) {
				listFrm.btnReset_click(null);
				listFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		txtStuName.setText("");
		txtLoginPwd.setText("");
		txtLoginName.setText("");
		rdoIsDisabledStart.setSelected(true);// 重置时默认选择启用
		lblMsg.setText("");
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		this.setTitle("添加教师");
	}

}
