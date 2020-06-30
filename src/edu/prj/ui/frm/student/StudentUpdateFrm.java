package edu.prj.ui.frm.student;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.ClassRoomBean;
import edu.prj.bean.StudentBean;
import edu.prj.service.ClassRoomService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.ClassRoomServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class StudentUpdateFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2902958131789321352L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public StudentListFrm studentListFrm = null;

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

	private JLabel lblRoomName;// 班级的标签
	private JComboBox<IdText> cboRoomName;// 班级 的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public StudentUpdateFrm() {
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
		int height = 400;
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
		if (this.studentListFrm != null) {
			studentListFrm.setVisible(true);
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

		lblRoomName = new JLabel();
		lblRoomName.setText("班级：");
		lblRoomName.setBounds(90, 160, 120, 150);
		container.add(lblRoomName);

		cboRoomName = new JComboBox<IdText>();
		cboRoomName.setBounds(140, 220, 160, 30);
		container.add(cboRoomName);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 280, 70, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 280, 70, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		// lblMsg.setText("提示信息");
		lblMsg.setBounds(70, 330, 180, 30);
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);
	}

	private void custBindEvent() {
		// 提交按钮事件处理
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSubmit_click(e);
			}

		});
		// 重置按钮事件处理
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnReset_click(e);
			}

		});
	}

	StudentService studentService = new StudentServiceImpl();
	ClassRoomService classRoomService = new ClassRoomServiceImpl();

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<ClassRoomBean> list = classRoomService.list();

		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		// idTextListA.add(new IdText(0L, "请选择"));
		for (ClassRoomBean item : list) {
			idTextList.add(new IdText(item.getRoomID(), item.getRoomName()));
		}
		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboRoomName.setModel(model);
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		// trim忽略前后空格
		String LoginName = txtLoginName.getText().trim();
		String LoginPwd = txtLoginPwd.getText().trim();
		String name = txtStuName.getText().trim();
		// 获取下拉菜单的RoomName
		IdText itemRoomName = (IdText) cboRoomName.getSelectedItem();
		// 获取下拉菜单的RoomName对应的RoomId
		Long roomId = itemRoomName.getId();
		// 2)为空性判断
		if (SysFun.isNullOrEmpty(LoginName)) {
			lblMsg.setText("提示:账号不能为空!");
			return;
		}
		if (LoginPwd == null || LoginPwd.isEmpty()) {
			lblMsg.setText("提示:密码不能为空!");
			return;
		}
		if (name == null || name.isEmpty()) {
			lblMsg.setText("提示:姓名不能为空!");
			return;

		}
		if (rdoIsDisabledStart.isSelected() == false && rdoIsDisabledBan.isSelected() == false) {
			lblMsg.setText("提示:请选择是否禁用");
			return;
		}
		if (cboRoomName.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择班级");
			return;
		}

		Long isDisabled = 0L;
		if (rdoIsDisabledStart.isSelected()) {
			isDisabled = 0L;
		} else if (rdoIsDisabledBan.isSelected()) {
			isDisabled = 1L;
		}

		// 修改时,根据名称取得对象
		// a)对象的主键如果与当前修改的主键一致,说明取得的是当前修改对象,允许修改
		// b)对象的主键如果与当前修改的主键不一致,说明存在另一个对象的名称跟当前修改内容一致,说明名称重复
		// 5)真正的修改
		StudentBean bean = new StudentBean();
		bean.setStudentID(pk);
		bean.setLoginName(LoginName);
		bean.setLoginPwd(LoginPwd);
		bean.setName(name);
		bean.setIsDisabled(isDisabled);
		bean.setRoomID(roomId);

		// 6)处理结果
		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;

		try {
			result = studentService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (studentListFrm != null) {
				studentListFrm.btnReset_click(null);
				studentListFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		bindComboBoxData();// 绑定下拉菜单数据
		loadData();//【修改窗体之加载数据--步骤2】根据pk加载要修改的主键对应的数据，并显示窗体的标题
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		bindComboBoxData();
		this.setTitle("修改学生信息");
	}

	/**
	 * 【修改窗体之加载数据--步骤1】声明当前修改的数据的主键值。从列表数据中传递过来
	 */
	Long pk = null;

	/**
	 * 【修改窗体之加载数据--步骤2】根据pk加载要修改的主键对应的数据，并显示窗体的标题
	 */
	public void loadData() {
		if (!this.getTitle().contains("主键")) {
			this.setTitle(this.getTitle() + "-->正在修改id为【" + pk + "】的学生信息");
		}
		StudentService studentService = new StudentServiceImpl();
		StudentBean bean = studentService.load(pk);

		if (bean != null) {
			txtLoginName.setText(bean.getLoginName());
			txtLoginPwd.setText(bean.getLoginPwd());
			txtStuName.setText(bean.getName());
			// 判断是否禁用,若为0,则启用单选框被选择.否则禁用单选框被选择
			if (bean.getIsDisabled() == 0) {
				rdoIsDisabledStart.setSelected(true);
			} else {
				rdoIsDisabledBan.setSelected(true);
			}
			cboRoomName.getModel().setSelectedItem(new IdText(bean.getRoomID(), ""));

		}
	}
}