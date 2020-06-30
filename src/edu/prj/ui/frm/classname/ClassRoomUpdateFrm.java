package edu.prj.ui.frm.classname;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.ClassRoomBean;
import edu.prj.bean.GradeBean;
import edu.prj.service.ClassRoomService;
import edu.prj.service.GradeService;
import edu.prj.service.impl.ClassRoomServiceImpl;
import edu.prj.service.impl.GradeServiceImpl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomUpdateFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1265686581081476098L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public ClassRoomListFrm classRoomListFrm = null;

	private JLabel lblRoomName;// 班级名称的标签
	private JTextField txtRoomName;// 班级名称的文本框

	private JLabel lblGradeName;// 年级名称的标签
	private JComboBox<IdText> cboGradeName;// 年级名称的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public ClassRoomUpdateFrm() {
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
		if (this.classRoomListFrm != null) {
			classRoomListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblRoomName = new JLabel();
		lblRoomName.setText("班级:");

		lblRoomName.setBounds(90, 60, 80, 30);
		container.add(lblRoomName);

		txtRoomName = new JTextField();
		txtRoomName.getText();

		txtRoomName.setBounds(140, 60, 160, 30);
		container.add(txtRoomName);

		lblGradeName = new JLabel();
		lblGradeName.setText("年级：");
		lblGradeName.setBounds(90, 65, 100, 100);
		container.add(lblGradeName);

		cboGradeName = new JComboBox<IdText>();
		cboGradeName.setBounds(140, 100, 160, 30);
		container.add(cboGradeName);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 150, 70, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 150, 70, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		// lblMsg.setText("提示信息");
		lblMsg.setBounds(70, 200, 180, 30);
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

	ClassRoomService classRoomService = new ClassRoomServiceImpl();
	GradeService gradeService = new GradeServiceImpl();

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<GradeBean> list = gradeService.list();

		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		// idTextListA.add(new IdText(0L, "请选择"));
		for (GradeBean item : list) {
			idTextList.add(new IdText(item.getGradeId(), item.getGradeName()));
		}
		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboGradeName.setModel(model);
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		// 1）获取输入数据
		// trim忽略前后空格
		String roomName = txtRoomName.getText().trim();// trim忽略前后空格
		IdText itemGradeName = (IdText) cboGradeName.getSelectedItem();
		Long gradeId = itemGradeName.getId();
		// 2)为空性判断
		if (SysFun.isNullOrEmpty(roomName)) {
			lblMsg.setText("提示：班级不能为空!");
			return;
		}
		if (cboGradeName.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择年级");
			return;
		}
		// 修改时,根据名称取得对象
		// a)对象的主键如果与当前修改的主键一致,说明取得的是当前修改对象,允许修改
		// b)对象的主键如果与当前修改的主键不一致,说明存在另一个对象的名称跟当前修改内容一致,说明名称重复
		// 5)真正的修改
		ClassRoomBean bean = new ClassRoomBean();
		bean.setRoomID(pk);
		bean.setRoomName(roomName);
		bean.setGradeID(gradeId);
		// 6)处理结果
		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;

		try {
			result = classRoomService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (classRoomListFrm != null) {
				classRoomListFrm.btnReset_click(null);
				classRoomListFrm.setVisible(true);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "操作失败!");
		}
	}

	// 重置功能
	private void btnReset_click(ActionEvent e) {
		bindComboBoxData();
		loadData();
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		bindComboBoxData();
		this.setTitle("修改班级信息");
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
			this.setTitle(this.getTitle() + "-->正在修改id为【" + pk + "】的班级信息");
		}

		ClassRoomBean bean = classRoomService.load(pk);

		if (bean != null) {
			txtRoomName.setText(bean.getRoomName());
			cboGradeName.getModel().setSelectedItem(new IdText(bean.getGradeID(), ""));

		}
	}
}