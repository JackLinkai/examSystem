package edu.prj.ui.frm.exam;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.TableModel;

import com.liuvei.common.win.ATableModel;

import edu.prj.bean.ExamBean;
import edu.prj.bean.StudentBean;
import edu.prj.service.ExamService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.ExamServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.frm.LoginFrm;

public class ExamListFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5822549542520418479L;
	// 为窗体添加Panel类型的容器对象container属性，容器对象才可以放置按钮，输入框等。
	// 在initUI（）里实例化后，还要设置容器的布局
	private JPanel container;

	private JButton btnUpdate;// 刷新界面按钮
	private JLabel lblTitle;// 管理界面的标题
	private JLabel lblMsg; // 提示信息的标签

	public LoginFrm loginFrm = null;
	public String loginName = null;
	ExamService examService = new ExamServiceImpl();

	/**
	 * 无参构造方法
	 */
	public ExamListFrm() {
		// 1.初始化用户界面
		initUI();
		// 2.绑定事件
		bindEvent();
		// 3.自定义加载
		customLoad();
	}

	// 初始化UI组件
	private void initUI() {
		// 定义当前窗体的宽高
		int width = 800;
		int height = 500;
		// 设置当前窗体的宽高
		this.setSize(width, height);
		// 计算当前窗体居中时的x,y坐标
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		// 定义当前窗体的位置
		this.setLocation(x, y);
		// 设置标题
		this.setTitle("考试");
		// 设置窗体不允许最大化，即不允许改变窗体大小
		this.setResizable(false);
		// 实例化容器对象
		container = new JPanel();
		// 把容器的布局设置为null，代表绝对布局
		container.setLayout(null);
		// 将容器对象添加到当前窗体
		this.add(container);

		// 管理界面标题
		lblTitle = new JLabel();
		lblTitle.setText("试 卷 信 息 列 表");
		lblTitle.setBounds(285, 5, 300, 80);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 25));
		container.add(lblTitle);

		// 添重置按钮
		btnUpdate = new JButton();
		btnUpdate.setText("刷新");
		// btnUpdate.setVisible(false);
		btnUpdate.setBounds(720, 420, 60, 40);
		container.add(btnUpdate);

		lblMsg = new JLabel();
		lblMsg.setText("提示：先按刷新按钮显示自己可以参加的考试，再右击表格中自己要进行的试卷行开始考试！");
		lblMsg.setBounds(5, 420, 700, 30);
		lblMsg.setFont(new Font("宋体", Font.BOLD, 16));
		lblMsg.setForeground(Color.RED);
		container.add(lblMsg);

		// 【Swing表格步骤4】：initUI()中初始化表格的方法
		initTableUI();

		// 【右键菜单--步骤5 】initUI()中初始化表格对象的右键菜单
		createTblObjMenu();
	}

	/**
	 * 【Swing表格步骤1】： pnlTablePane表格面板，用来放真正的表格对象
	 */

	private JScrollPane pnlTablePane;

	/**
	 * 【Swing表格步骤2】：tblObj是表格对象
	 */
	private JTable tblObj;

	/**
	 * 【Swing表格步骤3】：初始化表格相关对象，要调用获取表格数据的方法showListDate().
	 * <p>
	 * * 【Swing表格步骤4】：initUI()中初始化表格的方法
	 */
	// 初始化表格相关对象，要调用获取表格数据的方法showListData()
	private void initTableUI() {
		// TODO Auto-generated method stub
		// 初始化表格对象
		tblObj = new JTable();
		// 初始化面板的对象，并放入表格对象
		pnlTablePane = new JScrollPane(tblObj);

		// 设置表格面板的位置
		pnlTablePane.setBounds(50, 100, 700, 300);

		// 整个窗体的容器中添加表格面板
		container.add(pnlTablePane);

		// 调用方法在表格对象中显示List集合的方法
		showListData();
	}

	/**
	 * 2.绑定当前窗体的各个事件
	 */
	private void bindEvent() {
		// 自定义窗体关闭事件
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// 关闭当前窗体
		// 为当前窗体添加监听器：目的是重写windowsClosing事件的处理方法
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				window_closing(e);
			}

		});

		// 重置按钮事件处理
		btnUpdate.addActionListener(e -> {
			btnUpdate_click(e);
		});

//		添加右键点击事件会自动锁定当行,在表格对象的tblobj中，右击时，显示点击菜单
		tblObj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 判断点击的是鼠标右键
				if (e.getButton() == MouseEvent.BUTTON3) {
					// 获取鼠标所在位置对应的行号
					int rowIndex = tblObj.rowAtPoint(e.getPoint());
					// 如果行号为-1，则说明鼠标不在数据行
					if (rowIndex == -1) {
						return;
					}
					// 如果鼠标位置在数据行。则选中该行
					tblObj.setRowSelectionInterval(rowIndex, rowIndex);

					// 调用相关方法，显示右键菜单那=
					tblObjMenu.show(tblObj, e.getX(), e.getY());
				}
			};
		});

	}

	// 根据文本框的内容搜索对应的数据
	public void btnUpdate_click(ActionEvent e) {
		// TODO Auto-generated method stub
		showListData();
	}

	// 调用方法在表格对象中显示List集合的方法
	public void showListData() {
		// 从DB中取得List集合数据
		java.util.List<ExamBean> list = null;

		list = examService.listStuName(loginName);

		// 将list集合数据传给【所属表格模型】
		// 申明表格模型tableMOdel才是数据
		ATableModel<ExamBean> tableMOdel = null;

		tableMOdel = new ATableModel<ExamBean>(list, 9) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object getPropValue(ExamBean bean, int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return bean.getExamID();
				} else if (columnIndex == 1) {
					return bean.getPaperID();
				} else if (columnIndex == 2) {
					return bean.getPaperName();
				} else if (columnIndex == 3) {
					return bean.getStudentID();
				} else if (columnIndex == 4) {
					return bean.getName();
				} else if (columnIndex == 5) {
					return bean.getStartOn();
				} else if (columnIndex == 6) {
					return bean.getEndOn();
				} else if (columnIndex == 7) {
					return bean.getIsMark() == 0 ? "否" : "是";
				} else if (columnIndex == 8) {
					if (bean.getIsMark() == 0) {
						bean.setTotalScore(0.00);
					}
					return bean.getTotalScore();
				}
				return null;

			}

			@Override
			public String getTitle(int columnIndex) {
				// TODO Auto-generated method stub
				if (columnIndex == 0) {
					return "考试Id";
				} else if (columnIndex == 1) {
					return "试卷Id";
				} else if (columnIndex == 2) {
					return "试卷名称";
				} else if (columnIndex == 3) {
					return "学生Id";
				} else if (columnIndex == 4) {
					return "学生名称";
				} else if (columnIndex == 5) {
					return "有效开始时间";
				} else if (columnIndex == 6) {
					return "有效结束时间";
				} else if (columnIndex == 7) {
					return "是否阅卷";
				} else if (columnIndex == 8) {
					return "总分";
				}
				return null;
			}

		};

		// 将表格模型tableMOdel封装的列表数据list，显示到表格对象tblObj。
		tblObj.setModel(tableMOdel);

	}

	/**
	 * 【右键菜单--步骤1】定义表格对象的右键菜单
	 */
	private JPopupMenu tblObjMenu = null;

	/**
	 * 【右键菜单--步骤2】创建表格对象的右键菜单,并绑定事件
	 */

	private void createTblObjMenu() {
		tblObjMenu = new JPopupMenu();

		// 菜单项:开始考试菜单，添加到右键菜单中
		JMenuItem startExamItem = new JMenuItem("开始考试");
		tblObjMenu.add(startExamItem);

		// 开始考试菜单点击事件
		startExamItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startExamItem_click(e);
			}

		});

	}

	protected void startExamItem_click(ActionEvent e) {
		// TODO Auto-generated method stub
		int index = tblObj.getSelectedRow();
		// 如果下标不为0，则选中该数据行
		if (index != -1) {
			// 取得表格对象的数据模型
			TableModel model = tblObj.getModel();
			// 在表格的对象模型中，根据选中的行和列，取得对应的数据值
			Object obj = model.getValueAt(index, 0);
			Long pk = Long.valueOf(obj.toString());

			ExamBean bean = examService.load(pk);
			StudentService studentService = new StudentServiceImpl();
			StudentBean item = studentService.loadByName(loginName);
			boolean isOK = item.getStudentID() == bean.getStudentID();

			if (!isOK) {
				JOptionPane.showMessageDialog(null, "考生姓名与当前用户不符,不能进行考试");
				return;
			}
			if (istime(bean)) {

				if (bean.getIsMark() == 1) {
					JOptionPane.showMessageDialog(null, "考试已经结束，不可重复考试");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "不在答题时间内");
			}

			if (pk > 0) {
				// 选中当前试卷，进入考试界面。
				ExamItemListFrm examItemFrm = new ExamItemListFrm(pk);

				examItemFrm.setId(pk);
				examItemFrm.listFrm = this;
				this.setVisible(false);
				examItemFrm.setVisible(true);
				examItemFrm.timer.start();
			}

		}
	}

	private boolean istime(ExamBean exam) {
		Date date = new Date();
		try {
			return date.after(exam.getStartOn()) && date.before(exam.getEndOn());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		return rootPaneCheckingEnabled;

	}

	protected void window_closing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();
	}

	/**
	 * 3.自定义加载类
	 */
	public void customLoad() {
		this.setTitle("考试信息");
	}
}
