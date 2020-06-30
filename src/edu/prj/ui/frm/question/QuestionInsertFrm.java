package edu.prj.ui.frm.question;

import com.liuvei.common.SysFun;
import com.liuvei.common.win.IdText;
import com.liuvei.common.win.IdTextModel;

import edu.prj.bean.QuestionBean;
import edu.prj.bean.SubjectBean;
import edu.prj.service.QuestionService;
import edu.prj.service.SubjectService;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.service.impl.SubjectServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class QuestionInsertFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5107973246077245408L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;
	public QuestionListFrm listFrm = null;

	private JLabel lblQType;// 题目类型的标签
	private JComboBox<IdText> cboQType;// 题目类型的文本框

	private JLabel lblQuestion;// 题目的标签
	private JTextField txtQuestion;// 题目类型的文本框

	private JLabel lblItemA;// 选项A的标签
	private JTextField txtItemA;// 选项A的文本框

	private JLabel lblItemB;// 选项B的标签
	private JTextField txtItemB;// 选项B的文本框

	private JLabel lblItemC;// 选项C的标签
	private JTextField txtItemC;// 选项C的文本框

	private JLabel lblItemD;// 选项D的标签
	private JTextField txtItemD;// 选项D的文本框

	private JLabel lblItemE;// 选项E的标签
	private JTextField txtItemE;// 选项E的文本框

	private JLabel lblItemF;// 选项F的标签
	private JTextField txtItemF;// 选项F的文本框

	private JLabel lblAnswer;// 答案的标签
	private JTextField txtAnswer;// 答案的文本框

	private JLabel lblSubjectName;// 科目名称的标签
	private JComboBox<IdText> cboSubjectName;// 科目名称的文本框

	private JLabel lblTag;// 标签的标签
	private JTextField txtTag;// 标签的文本框

	private JLabel lblNotice;// 注意的标签

	private JButton btnSubmit;// 提交按钮
	private JButton btnReset;// 重置按钮

	private JLabel lblMsg; // 提示信息的标签

	/**
	 * 无参构造方法
	 */
	public QuestionInsertFrm() {
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
		int width = 600;
		int height = 800;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("题库添加");
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
//				window_closing(e);
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

		lblQType = new JLabel();
		lblQType.setText("题目类型：");
		lblQType.setBounds(150, 60, 80, 30);
		lblQType.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblQType);

		cboQType = new JComboBox<IdText>();
		cboQType.setBounds(230, 60, 200, 30);
		container.add(cboQType);

		lblQuestion = new JLabel();
		lblQuestion.setText("题目：");
		lblQuestion.setBounds(158, 110, 80, 30);
		lblQuestion.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblQuestion);

		txtQuestion = new JTextField();
		txtQuestion.getText();
		txtQuestion.setBounds(230, 110, 200, 30);
		container.add(txtQuestion);

		lblItemA = new JLabel();
		lblItemA.setText("选项A：");
		lblItemA.setBounds(155, 160, 80, 30);
		lblItemA.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblItemA);

		txtItemA = new JTextField();
		txtItemA.getText();
		txtItemA.setBounds(230, 160, 200, 30);
		container.add(txtItemA);

		lblItemB = new JLabel();
		lblItemB.setText("选项B：");
		lblItemB.setBounds(155, 210, 80, 30);
		lblItemB.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblItemB);

		txtItemB = new JTextField();
		txtItemB.getText();
		txtItemB.setBounds(230, 210, 200, 30);
		container.add(txtItemB);

		lblItemC = new JLabel();
		lblItemC.setText("选项C：");
		lblItemC.setBounds(155, 260, 80, 30);
		lblItemC.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblItemC);

		txtItemC = new JTextField();
		txtItemC.getText();
		txtItemC.setBounds(230, 260, 200, 30);
		container.add(txtItemC);

		lblItemD = new JLabel();
		lblItemD.setText("选项D：");
		lblItemD.setBounds(155, 310, 80, 30);
		lblItemD.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblItemD);

		txtItemD = new JTextField();
		txtItemD.getText();
		txtItemD.setBounds(230, 310, 200, 30);
		container.add(txtItemD);

		lblItemE = new JLabel();
		lblItemE.setText("选项E：");
		lblItemE.setBounds(155, 360, 80, 30);
		lblItemE.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblItemE);

		txtItemE = new JTextField();
		txtItemE.getText();
		txtItemE.setBounds(230, 360, 200, 30);
		container.add(txtItemE);

		txtItemE.setText("对");
		txtItemE.setEnabled(false);

		lblItemF = new JLabel();
		lblItemF.setText("选项F：");
		lblItemF.setBounds(155, 410, 80, 30);
		lblItemF.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblItemF);

		txtItemF = new JTextField();
		txtItemF.getText();
		txtItemF.setBounds(230, 410, 200, 30);
		container.add(txtItemF);

		txtItemF.setText("错");
		txtItemF.setEnabled(false);

		lblAnswer = new JLabel();
		lblAnswer.setText("答案：");
		lblAnswer.setBounds(160, 460, 80, 30);
		lblAnswer.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblAnswer);

		txtAnswer = new JTextField();
		txtAnswer.getText();
		txtAnswer.setBounds(230, 460, 200, 30);
		container.add(txtAnswer);

		lblSubjectName = new JLabel();
		lblSubjectName.setText("科目名称：");
		lblSubjectName.setBounds(150, 510, 80, 30);
		lblSubjectName.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblSubjectName);

		cboSubjectName = new JComboBox<IdText>();
		cboSubjectName.setBounds(230, 510, 200, 30);
		container.add(cboSubjectName);

		lblTag = new JLabel();
		lblTag.setText("标签：");
		lblTag.setBounds(160, 560, 80, 30);
		lblTag.setFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		container.add(lblTag);

		txtTag = new JTextField();
		txtTag.getText();
		txtTag.setBounds(230, 560, 200, 30);
		container.add(txtTag);

		lblNotice = new JLabel();
		lblNotice.setText("注意：如果题目类型为判断题,答案只能为E或F,ABCD为空；如果题目类型为选择题,答案不能为E或F！");
		lblNotice.setBounds(5, 610, 600, 30);
		lblNotice.setFont(new Font("宋体", Font.CENTER_BASELINE, 12));
		lblNotice.setForeground(Color.blue);
		container.add(lblNotice);

		btnSubmit = new JButton();
		btnSubmit.setText("提交");
		btnSubmit.setBounds(190, 660, 90, 40);
		container.add(btnSubmit);

		btnReset = new JButton();
		btnReset.setText("重置");
		btnReset.setBounds(350, 660, 90, 40);
		container.add(btnReset);

		lblMsg = new JLabel();
		lblMsg.setBounds(150, 720, 600, 30);
		lblMsg.setFont(new Font("宋体", Font.CENTER_BASELINE, 14));
		lblMsg.setForeground(Color.red);
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
	QuestionService questionService = new QuestionServiceImpl();

	private void bindComboBoxData() {
		// 绑定下拉菜单数据的过程
		// 1)从数据库加载列表
		List<SubjectBean> list = subjectService.list();
		// 2)将转为list<Idtext>
		List<IdText> idTextList = new ArrayList<IdText>();
		idTextList.add(new IdText(0L, "-----------请选择-----------"));
		for (SubjectBean item : list) {
			idTextList.add(new IdText(item.getSubjectID(), item.getSubjectName()));
		}
		// 3)传递给IdTextModel
		IdTextModel model = new IdTextModel(idTextList);
		// 4)再显示到cbo控件
		cboSubjectName.setModel(model);
		cboSubjectName.setSelectedIndex(0);

		List<IdText> idTextListC = new ArrayList<IdText>();
		idTextListC.add(new IdText(0L, "-----------请选择-----------"));
		idTextListC.add(new IdText(1L, "判断题"));
		idTextListC.add(new IdText(2L, "单选题"));
		idTextListC.add(new IdText(3L, "多选题"));
		IdTextModel modelC = new IdTextModel(idTextListC);
		cboQType.setModel(modelC);
		cboQType.setSelectedIndex(0);// 默认选中的下标
//		cboQType.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					if (cboQType.getSelectedItem().equals("判断题")) {
//						txtItemA.setEditable(false);
//						txtItemB.setEditable(false);
//						txtItemC.setEditable(false);
//						txtItemD.setEditable(false);
//						txtItemE.setText("对");
//						txtItemE.setEnabled(false);
//						txtItemF.setText("错");
//						txtItemF.setEnabled(false);
//						return;
//
//					}
//					if (cboQType.getSelectedItem().equals("单选题") || cboQType.getSelectedItem().equals("多选题")) {
//						txtItemE.setEditable(false);
//						txtItemF.setEditable(false);
//						return;
//					}
//
//				}
//			}
//
//		});
	}

	// 提交功能
	@SuppressWarnings("unlikely-arg-type")
	private void btnSubmit_click(ActionEvent e) {
		lblMsg.setText("");// 每次提交,先清空上次的提示信息
		// 1）获取输入数据
		String question = txtQuestion.getText().trim();// trim忽略前后空格
		String itemA = txtItemA.getText().trim();
		String itemB = txtItemB.getText().trim();
		String itemC = txtItemC.getText().trim();
		String itemD = txtItemD.getText().trim();
		String answer = txtAnswer.getText().trim();
		String tag = txtTag.getText().trim();
		IdText itemQType = (IdText) cboQType.getSelectedItem();
		IdText itemSubjectName = (IdText) cboSubjectName.getSelectedItem();
		Long qType = itemQType.getId();
		Long subjectName = itemSubjectName.getId();
		// 2)为空性判断
		if (cboQType.getSelectedIndex() == 0) {
			lblMsg.setText("提示：请选择题目类型");
			return;
		}
		if (cboQType.getSelectedIndex() == 1) {
			if (!"E".equals(answer) && !"F".equals(answer)) {
				lblMsg.setText("判断题答案只能输入“E,F”,答案输入错误!");
				return;
			}
			if (!SysFun.isNullOrEmpty(itemA) || !SysFun.isNullOrEmpty(itemB) || !SysFun.isNullOrEmpty(itemC)
					|| !SysFun.isNullOrEmpty(itemD)) {
				lblMsg.setText("判断题选项ABCD必须为空");
				return;
			}
		}
		if (cboQType.getSelectedIndex() == 2 || cboQType.getSelectedIndex() == 3) {
			if (SysFun.isNullOrEmpty(itemA)) {
				lblMsg.setText("提示:选项A不能为空!");
				return;
			}
			if (SysFun.isNullOrEmpty(itemB)) {
				lblMsg.setText("提示:选项B不能为空!");
				return;
			}
			if (SysFun.isNullOrEmpty(itemC)) {
				lblMsg.setText("提示:选项C不能为空!");
				return;
			}
			if (SysFun.isNullOrEmpty(itemD)) {
				lblMsg.setText("提示:选项D不能为空!");
				return;
			}
		}
		if (cboQType.getSelectedIndex() == 2) {
			if ("A".equals(answer) || "B".equals(answer) || "C".equals(answer) || "D".equals(answer)) {
				lblMsg.setText("单选题答案只能输入“A,B,C,D”,答案输入错误!");
				return;
			}
		}
		if (cboQType.getSelectedIndex() == 3) {
			if ("AB".equals(answer) || "AC".equals(answer) || "AD".equals(answer) || "BC".equals(answer)
					|| "BD".equals(answer) || "CD".equals(answer) || "ABC".equals(answer) || "ABD".equals(answer)
					|| "ACD".equals(answer) || "BCD".equals(answer)) {
				lblMsg.setText("答案输入错误!");
				return;
			}
		}
		if (cboSubjectName.getSelectedIndex() == 0) {
			lblMsg.setText("提示：请选择科目名称");
			return;
		}
		if (SysFun.isNullOrEmpty(question)) {
			lblMsg.setText("提示:题目不能为空!");
			return;
		}
		if (SysFun.isNullOrEmpty(answer)) {
			lblMsg.setText("提示:答案不能为空!");
			return;
		}

		// 8)真正的添加
		QuestionBean bean = new QuestionBean();
		bean.setQType(qType);
		if (bean.getQType().equals(1L)) {
			bean.setQuestion(question);
			bean.setItemA(itemA);
			bean.setItemB(itemB);
			bean.setItemC(itemC);
			bean.setItemD(itemD);
			bean.setItemE("对");
			bean.setItemF("错");
		} else {
			bean.setQuestion(question);
			bean.setItemA(itemA);
			bean.setItemB(itemB);
			bean.setItemC(itemC);
			bean.setItemD(itemD);
			bean.setItemE(null);
			bean.setItemF(null);
		}
		bean.setAnswer(answer);
		bean.setTag(tag);
		bean.setSubjectID(subjectName);

		Long result = 0L;
		@SuppressWarnings("unused")
		String errMsg = null;
		try {
			result = questionService.insert(bean);
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
		txtQuestion.setText("");
		txtItemA.setText("");
		txtItemB.setText("");
		txtItemC.setText("");
		txtItemD.setText("");
		txtAnswer.setText("");
		txtTag.setText("");
		cboQType.setSelectedIndex(0);
		cboSubjectName.setSelectedIndex(0);
		lblMsg.setText("");
	}

	/*
	 * 3.自定义加载:在初始化UI和绑定事件之后执行
	 */
	public void customLoad() {
		this.setTitle("添加题库");
		bindComboBoxData();

	}

}
