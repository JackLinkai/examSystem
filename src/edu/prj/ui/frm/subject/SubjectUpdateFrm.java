package edu.prj.ui.frm.subject;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.SubjectBean;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.SubjectServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SubjectUpdateFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7484612304036667664L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public SubjectListFrm subjectListFrm = null;

	private JLabel lblSubjectName;// 科目名称的标签
	private JTextField txtSubjectName;// 科目名称的文本框

	private JLabel lblStatus;// 状态的标签
	private JComboBox<IdText> cboStatus;// 状态的文本框

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public SubjectUpdateFrm() {
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
		this.setTitle("科目管理");
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
		if (this.subjectListFrm != null) {
			subjectListFrm.setVisible(true);
		}
		this.dispose();
	}

	private void customInitUI() {

		lblSubjectName = new JLabel();
		lblSubjectName.setText("科目名称：");

		lblSubjectName.setBounds(90, 60, 80, 30);
		container.add(lblSubjectName);
		txtSubjectName = new JTextField();
		txtSubjectName.getText();

		txtSubjectName.setBounds(160, 60, 160, 30);
		container.add(txtSubjectName);

		lblStatus = new JLabel();
		lblStatus.setText("状态：");
		lblStatus.setBounds(100, 65, 120, 150);
		container.add(lblStatus);

		cboStatus = new JComboBox<IdText>();
		cboStatus.setBounds(160, 125, 160, 30);
		container.add(cboStatus);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(130, 180, 70, 30);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(230, 180, 70, 30);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(70, 210, 180, 30);
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

	SubjectService subjectService = new SubjectServiceImpl();

	private void bindComboBoxData() {
		java.util.List<IdText> idTextList = new java.util.ArrayList<IdText>();
		idTextList.add(new IdText(0L, "开放"));
		idTextList.add(new IdText(1L, "关闭"));
		IdTextModel model = new IdTextModel(idTextList);
		cboStatus.setModel(model);
	}

	// 提交功能
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据
		String subjectName = txtSubjectName.getText().trim();// trim忽略前后空格
		IdText itemStatus = (IdText) cboStatus.getSelectedItem();
		Long status = itemStatus.getId();
		// 2)为空性判断

		if (SysFun.isNullOrEmpty(subjectName)) {
			lblMsg.setText("提示：科目名称不能为空！");
			return;
		}
		if (cboStatus.getSelectedIndex() == -1) {
			lblMsg.setText("提示：请选择状态");
			return;
		}
		// 修改时,根据名称取得对象
		// a)对象的主键如果与当前修改的主键一致,说明取得的是当前修改对象,允许修改
		// b)对象的主键如果与当前修改的主键不一致,说明存在另一个对象的名称跟当前修改内容一致,说明名称重复
		// 5)真正的修改
		SubjectBean bean = new SubjectBean();
		bean.setSubjectID(pk);
		bean.setSubjectName(subjectName);
		bean.setStatus(status);

		// 6)处理结果
		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;

		try {
			result = subjectService.update(bean);
		} catch (Exception ex) {
			errMsg = ex.getMessage();
		}
		// 9)处理结果修改结果
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "操作成功!");
			// 成功时,重置并显示父窗体,之后,关闭当前操作
			if (subjectListFrm != null) {
				subjectListFrm.btnReset_click(null);
				subjectListFrm.setVisible(true);
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
		this.setTitle("修改科目信息");
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
		SubjectBean bean = subjectService.load(pk);

		if (bean != null) {
			txtSubjectName.setText(bean.getSubjectName());
			cboStatus.getModel().setSelectedItem(new IdText(bean.getStatus(), ""));

		}
	}
}