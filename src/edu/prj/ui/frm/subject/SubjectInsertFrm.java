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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SubjectInsertFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8958987755450311600L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public SubjectListFrm listFrm = null;

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
	public SubjectInsertFrm() {
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
		if (listFrm != null) {
			listFrm.setVisible(true);
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
		btnSubmit.addActionListener(e -> {
			btnSubmit_click(e);
		});
		// 重置按钮事件处理
		btnReset.addActionListener(e -> {
			btnReset_click(e);
		});
	}

	SubjectService subjectService = new SubjectServiceImpl();

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		java.util.List<IdText> idTextList = new java.util.ArrayList<IdText>();
		idTextList.add(new IdText(0L, "--------请选择状态--------"));
		idTextList.add(new IdText(0L, "开放"));
		idTextList.add(new IdText(1L, "关闭"));
		IdTextModel model = new IdTextModel(idTextList);
		cboStatus.setModel(model);
		cboStatus.setSelectedIndex(0);

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
		// 8)真正的添加
		SubjectBean bean = new SubjectBean();
		bean.setSubjectName(subjectName);
		bean.setStatus(status);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = subjectService.insert(bean);
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
		txtSubjectName.setText("");
		lblMsg.setText("");
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		this.setTitle("添加科目");
		bindComboBoxData();
	}

}
